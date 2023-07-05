package com.energy.api.repository;

import com.energy.api.entity.Address;
import com.energy.api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
