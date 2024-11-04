package com.uol.compass.api_user.dto.mappers;

import com.uol.compass.api_user.dto.AddressResponseDTO;
import com.uol.compass.api_user.entities.Address;
import com.uol.compass.api_user.integrations.viacep.dto.AddressDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {

	public static AddressResponseDTO toDTO(Address obj) {
		AddressResponseDTO dto = new AddressResponseDTO();
		dto.setZipCode(obj.getZipCode());
		dto.setStreet(obj.getStreet());
		dto.setComplement(obj.getComplement());
		dto.setNeighborhood(obj.getNeighborhood());
		dto.setCity(obj.getCity());
		dto.setState(obj.getState());
		return dto;
	}
	
	public static Address toAddress(AddressDTO dto) {
		Address obj = new Address();
		obj.setZipCode(dto.getZipCode());
		obj.setStreet(dto.getStreet());
		obj.setComplement(dto.getComplement());
		obj.setNeighborhood(dto.getNeighborhood());
		obj.setCity(dto.getCity());
		obj.setState(dto.getState());
		return obj;
	}

}
