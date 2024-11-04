package com.uol.compass.api_user.dto.mappers;

import com.uol.compass.api_user.dto.UserRegisterDTO;
import com.uol.compass.api_user.dto.UserResponseDTO;
import com.uol.compass.api_user.entities.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

	public static User toUser(UserRegisterDTO dto) {
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		return user;
	}

	public static UserResponseDTO toDTO(User user) {
		UserResponseDTO userDTO = new UserResponseDTO();
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());
		userDTO.setAddress(AddressMapper.toDTO(user.getAddress()));
		return userDTO;
	}

}
