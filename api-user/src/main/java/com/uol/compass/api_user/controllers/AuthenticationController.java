package com.uol.compass.api_user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uol.compass.api_user.controllers.exceptions.ErrorMessage;
import com.uol.compass.api_user.dto.UserLoginDTO;
import com.uol.compass.api_user.jwt.JwtToken;
import com.uol.compass.api_user.jwt.JwtUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private JwtUserDetailsService detailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping
	public ResponseEntity<?> authenticate(@Valid @RequestBody UserLoginDTO dto, HttpServletRequest request) {
		try {
			UsernamePasswordAuthenticationToken auhtenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
			
			authenticationManager.authenticate(auhtenticationToken);
			
			JwtToken token = detailsService.getTokenAuthenticated(dto.getEmail());
			
			return ResponseEntity.ok(token);
			
		} catch (AuthenticationException e) {
			log.error("Bad credentials for username: " + dto.getEmail());
		}
		return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Invalid credencials"));
	}
}
