package com.uol.compass.api_user.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {
	private static final long serialVersionUID = 1L;

	private com.uol.compass.api_user.entities.User user;

	public JwtUserDetails(com.uol.compass.api_user.entities.User user) {
		super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
		this.user = user;
	}
	
	public Integer getId() {
		return this.user.getId();
	}

	public String getRole() {
		return this.user.getRole().name();
	}
}
