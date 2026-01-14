package com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.ForgotPasswordRequest;
import com.user.dto.ForgotPasswordResponse;
import com.user.dto.LoginRequest;
import com.user.dto.LoginResponse;
import com.user.dto.SignUpRequest;
import com.user.dto.SignUpResponse;
import com.user.service.IUserAuthService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class UserAuthController {

	private final IUserAuthService userAuthService;
	
	@PostMapping("/sign_up")
	public ResponseEntity<SignUpResponse> userRegistraion(@RequestBody SignUpRequest request){
		SignUpResponse userSignUp = userAuthService.userSignUp(request);
		return ResponseEntity.ok(userSignUp);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest  request){
		LoginResponse userLogin = userAuthService.userLogin(request);
		return ResponseEntity.ok(userLogin);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> userLogout(){
		userAuthService.userLogout();
		return ResponseEntity.ok("Logout successfully !");
	}
	
	@PostMapping("/forgot_password")
	public ResponseEntity<ForgotPasswordResponse> userResetPassword(@RequestBody ForgotPasswordRequest request){
		ForgotPasswordResponse forgotPassword = userAuthService.forgotPassword(request);
		return ResponseEntity.ok(forgotPassword);
	}
}
