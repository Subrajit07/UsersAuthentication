package com.user.dto;

import jakarta.validation.constraints.NotNull;

public record ForgotPasswordRequest(@NotNull(message = "username must required")
																			String username,
																			@NotNull(message = "new-password must required")
																			String newPassword,
																			@NotNull(message = "confirm-password must required")
																			String confirmPassword
																			) {

}
