package com.uol.compass.api_user.integrations.viacep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uol.compass.api_user.integrations.viacep.dto.AddressDTO;

@FeignClient(name = "viaCep", url = "https://viacep.com.br/ws")
public interface AddressFeign {

	@GetMapping("{cep}/json")
	AddressDTO getAddressByCep(@PathVariable("cep") String cep);
	
}
