package com.uol.compass.api_user.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {
	
	private String zipCode;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;

}
