package com.user.config;

import java.util.EnumSet;
import java.util.Set;

import com.user.enums.UserRole;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Common {

	public static Set<UserRole> determineRoles(UserRole requestedRole) {
        return switch (requestedRole) {
            case ADMIN -> EnumSet.of(UserRole.ADMIN, UserRole.MANAGER, UserRole.EMPLOYEE, UserRole.GUEST);
            case MANAGER -> EnumSet.of(UserRole.MANAGER, UserRole.EMPLOYEE,UserRole.GUEST);
            case EMPLOYEE ->EnumSet.of(UserRole.EMPLOYEE,UserRole.GUEST);
            default -> EnumSet.of(UserRole.GUEST);
        };
    }
}
