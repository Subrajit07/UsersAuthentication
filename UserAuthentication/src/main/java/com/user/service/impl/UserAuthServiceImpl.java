package com.user.service.impl;

import org.springframework.stereotype.Service;

import com.user.dto.LoginRequest;
import com.user.dto.LoginResponse;
import com.user.dto.SignUpRequest;
import com.user.dto.SignUpResponse;
import com.user.service.IUserAuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements IUserAuthService {

	@Override
	public LoginResponse userLogin(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SignUpResponse userSignUp(SignUpRequest signUpRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
