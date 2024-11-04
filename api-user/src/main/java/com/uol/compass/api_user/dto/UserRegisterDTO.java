package com.uol.compass.api_user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegisterDTO {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Pattern(regexp = "\\d{5}-?\\d{3}", message = "Invalid CEP format. Valid format: 12345-678 or 12345678")
	private String cep;
	
}
