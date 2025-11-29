package com.user.service;

import com.user.dto.LoginRequest;
import com.user.dto.LoginResponse;
import com.user.dto.SignUpRequest;
import com.user.dto.SignUpResponse;

public interface IUserAuthService {

	LoginResponse userLogin(LoginRequest loginRequest);
	
	SignUpResponse userSignUp(SignUpRequest signUpRequest);
	
}
