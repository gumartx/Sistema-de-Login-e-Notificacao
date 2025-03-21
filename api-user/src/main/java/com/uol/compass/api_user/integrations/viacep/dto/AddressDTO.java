package com.uol.compass.api_user.integrations.viacep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AddressDTO {
	
	@JsonProperty("cep")
	private String zipCode;
	@JsonProperty("logradouro")
    private String street;
	@JsonProperty("complemento")
    private String complement;
	@JsonProperty("bairro")
    private String neighborhood;
	@JsonProperty("localidade")
    private String city;
	@JsonProperty("uf")
    private String state;
	
}
