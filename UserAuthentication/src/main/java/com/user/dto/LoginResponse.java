package com.user.dto;

import java.util.Set;

import com.user.enums.UserRole;
public record LoginResponse(Long userId,
															String email,
															String jwtToken,
															Set<UserRole> roles
															){
	
}
