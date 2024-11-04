package com.uol.compass.api_user.dto;

import lombok.Data;

@Data
public class UserResponseDTO {

	private String username;
	private String email;
	private AddressResponseDTO address;
	
}
