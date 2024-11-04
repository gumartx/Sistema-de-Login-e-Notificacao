package com.uol.compass.api_user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uol.compass.api_user.entities.Address;

public interface AddressRepository extends JpaRepository<Address, String> {

}
