package com.uol.compass.api_user.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uol.compass.api_user.dto.mappers.AddressMapper;
import com.uol.compass.api_user.entities.Address;
import com.uol.compass.api_user.entities.User;
import com.uol.compass.api_user.entities.User.Role;
import com.uol.compass.api_user.exceptions.PasswordException;
import com.uol.compass.api_user.exceptions.ResourceNotFoundException;
import com.uol.compass.api_user.integrations.viacep.AddressFeign;
import com.uol.compass.api_user.repositories.AddressRepository;
import com.uol.compass.api_user.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final AddressFeign addressFeign;
	private final UserRepository repository;
	private final AddressRepository addressRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public User register(User obj, String cep) {
		Address address = addressRepository.save(AddressMapper.toAddress(addressFeign.getAddressByCep(cep)));
		if (address == null) {
			throw new ResourceNotFoundException(String.format("CEP '%s' not found", cep));
		}
		obj.setAddress(address);
		obj.setPassword(passwordEncoder.encode(obj.getPassword()));
		return repository.save(obj);
	}

	@Transactional
	public User updatePassword(String username, String oldPassword, String newPassword) {
		User obj = repository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		if (!passwordEncoder.matches(oldPassword, obj.getPassword())) {
			throw new PasswordException("The old password does not match with the current");
		}
		
		if (passwordEncoder.matches(newPassword, obj.getPassword())) {
			throw new PasswordException("The new password need to be different from the current");
		}

		obj.setPassword(passwordEncoder.encode(newPassword));
		
		return repository.save(obj);
	}

	public User findByEmail(String email) {
		User obj = repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		return obj;
	}

	public Role findRoleByEmail(String email) {
		Role role = repository.findRoleByEmail(email);
		return role;
	}

}
