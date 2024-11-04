package com.uol.compass.api_user.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uol.compass.api_user.entities.User;
import com.uol.compass.api_user.entities.User.Role;
import com.uol.compass.api_user.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserService service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = service.findByEmail(email);
		return new JwtUserDetails(user);
	}
	
	public JwtToken getTokenAuthenticated(String email) {
		Role role = service.findRoleByEmail(email);
		return JwtUtils.createToken(email, role.name().substring("ROLE_".length()));
	}
	
}
