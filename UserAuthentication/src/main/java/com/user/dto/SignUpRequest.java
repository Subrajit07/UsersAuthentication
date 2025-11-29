package com.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(@NotBlank(message = "Firstname must required")
															String firstName,
															@NotBlank(message = "Lastname must required")
															String lastName,
															@NotBlank(message="Email must required")
															@Email(message = "Invalid email")
															String email,
															@NotBlank(message="Password must required")
															@Size(min = 8,message = "Password should be above 8 characters")
															String password,
															@NotBlank(message="Confirm password must required")
															@Size(min = 8,message = "Confirm password should be above 8 characters")
															String confirmPassword,
															@NotBlank(message="phone number can not be blank")
															@Size(min=8,message = "Invalid mobile number")
															Long phoneNumber
														) {

}
