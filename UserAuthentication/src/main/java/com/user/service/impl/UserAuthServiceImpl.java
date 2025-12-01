package com.user.service.impl;

import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Map;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.user.config.Common;
import com.user.config.JwtUtils;
import com.user.config.MailService;
import com.user.dto.LoginRequest;
import com.user.dto.LoginResponse;
import com.user.dto.MailParameters;
import com.user.dto.SignUpRequest;
import com.user.dto.SignUpResponse;
import com.user.entity.Companies;
import com.user.entity.Employees;
import com.user.entity.Users;
import com.user.enums.UserRole;
import com.user.handler.PasswordMismatchException;
import com.user.handler.UserNameExistException;
import com.user.repo.ICompaniesRepo;
import com.user.repo.IEmployeesRepo;
import com.user.repo.IUserRepository;
import com.user.service.IUserAuthService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthServiceImpl implements IUserAuthService {

	private final IUserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final IEmployeesRepo employeesRepo;
	private final ICompaniesRepo companiesRepo;
	private final MailService mailService;
	private final JwtUtils jwtUtils;
	private final SpringTemplateEngine templateEngine;

	@Override
	public LoginResponse userLogin(LoginRequest loginRequest) {
		Users user = userRepo.findByUsername(loginRequest.username())
												  .orElseThrow(()->new UsernameNotFoundException("User not found"));
		if(!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}
		user.setLastLoginAt(OffsetDateTime.now());
		String token = jwtUtils.generateToken(user);
		userRepo.save(user);
		Employees emps=employeesRepo.findByEmail(user.getUsername());
		
		return new LoginResponse(user.getId(),emps.getEmail(), emps.getFullName(), token, user.getUserRoles());
	}

	@Override
	@Transactional
	public SignUpResponse userSignUp(SignUpRequest signUpRequest) {
		if (userRepo.existsByUsername(signUpRequest.email())) {
			log.warn("User with username " + signUpRequest.email() + " is already exist");
			throw new UserNameExistException("Username is already exist!");
		}
		if (!signUpRequest.password().equals(signUpRequest.confirmPassword())) {
			log.warn("Password & confirm password is not matched!");
			throw new PasswordMismatchException("Confirm password does not match with password");
		}
		Users user = saveUserData(signUpRequest.email(), signUpRequest.password());
		Employees employee = saveEmployeeData(signUpRequest.firstName(), signUpRequest.lastName(), signUpRequest.email(), signUpRequest.phoneNumber());
		Companies company = saveCompanyData(signUpRequest.companyName());
		Map<String, Object> data=Map.of("name",signUpRequest.firstName(),
																			"companyName",signUpRequest.companyName());
		String renderToTemplate = renderToTemplate("welcome.html", data);
		MailParameters mailParam = MailParameters.builder()
									  .to(employee.getEmail())
									  .subject("Welcome buddy!")
									  .body(renderToTemplate)
									  .isHtml(true).build();
		mailService.sendMail(mailParam);
		
		log.info("User signup successfully ! username :"+user.getUsername());
		return new SignUpResponse(user.getId(), user.getUsername(), user.getUserRoles());
	}

	private Companies saveCompanyData(String companyName) {
		 Companies company = companiesRepo.findByNameIgnoreCase(companyName)
						            .orElseGet(() -> {
						                return companiesRepo.save(
						                        Companies.builder()
						                                .name(companyName)
						                                .createdAt(OffsetDateTime.now())
						                                .build()
						                );
						            });
		 return company;
	}

	private Employees saveEmployeeData(String fName, String lName, String email, String mobileNo) {
		Employees employee = employeesRepo.save(Employees.builder().fullName(fName+" "+lName)
														  											 .email(email)
																									 .mobileNo(mobileNo)
																									 .build());
		return employeesRepo.save(employee);
	}

	private Users saveUserData(String username, String password) {
		Users user = Users.builder()
										   .username(username)
										   .password(passwordEncoder.encode(password))
										   .isActive(true)
										   .userRoles(Common.determineRoles(UserRole.GUEST)).build();
		return userRepo.save(user);
	}
	
	private String renderToTemplate(String templateName,Map<String, Object> data) {
		return templateEngine.process(templateName, new Context(Locale.getDefault(),data));
	}

}
