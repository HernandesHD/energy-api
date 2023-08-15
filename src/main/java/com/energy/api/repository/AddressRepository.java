package com.energy.api.repository;

import com.energy.api.entity.Address;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
=======
import com.energy.api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

>>>>>>> 8c86a07bf7eb7a0225ca11318b5ae43963298b73
public interface AddressRepository extends JpaRepository<Address, Long> {

}
