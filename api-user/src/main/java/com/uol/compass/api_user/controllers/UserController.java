package com.uol.compass.api_user.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uol.compass.api_user.dto.UserRegisterDTO;
import com.uol.compass.api_user.dto.UserResponseDTO;
import com.uol.compass.api_user.dto.UserUpdatePasswordDTO;
import com.uol.compass.api_user.dto.mappers.UserMapper;
import com.uol.compass.api_user.entities.User;
import com.uol.compass.api_user.services.KafkaProducerService;
import com.uol.compass.api_user.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService service;
	private final KafkaProducerService kafkaProducerService;
	
	@PostMapping(value = "/register")
	public ResponseEntity<UserResponseDTO> register (@Valid @RequestBody UserRegisterDTO dto) {
		User obj = service.register(UserMapper.toUser(dto), dto.getCep());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getId()).toUri();
		kafkaProducerService.sendMessage("CREATE", obj.getUsername());
		return ResponseEntity.created(uri).body(UserMapper.toDTO(obj));

	}
	
	@PutMapping(value = "/update-password")
	@PreAuthorize("hasRole('CLIENT') AND #dto.username == authentication.principal.username")
	public ResponseEntity<Void> updatePassword (@Valid @RequestBody UserUpdatePasswordDTO dto) {
		User obj = service.updatePassword(dto.getUsername(), dto.getOldPassword(), dto.getNewPassword());
		kafkaProducerService.sendMessage("UPDATE", obj.getUsername());
		return ResponseEntity.noContent().build();
	}
}
