package com.user.dto;

import java.util.Set;

import com.user.enums.UserRole;

public record SignUpResponse( Long userId,
																String username,
																Set<UserRole> userRoles
																) {

}
