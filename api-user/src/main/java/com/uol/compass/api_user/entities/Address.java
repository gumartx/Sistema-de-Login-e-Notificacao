package com.uol.compass.api_user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Address {

	@Id
	private String zipCode;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
	
}
