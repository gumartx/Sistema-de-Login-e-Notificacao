package com.uol.compass.api_user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdatePasswordDTO {

	@NotBlank
	private String username;
	@NotBlank
	private String oldPassword;
	@NotBlank
	private String newPassword;
	
}
