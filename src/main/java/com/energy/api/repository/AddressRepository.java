package com.energy.api.repository;

import com.energy.api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.energy.api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
