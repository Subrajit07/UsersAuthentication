package com.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
		@Email(message = "Invalid Email format!")
		@NotBlank(message = "username must required")
		String username,
		@NotBlank(message = "password must required")
		String password
		) {

}
