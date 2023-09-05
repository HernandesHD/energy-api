package com.energy.api.service;

import com.energy.api.entity.Address;
import com.energy.api.entity.AddressDTO;
import com.energy.api.entity.Person;
import com.energy.api.exceptions.PersonAlreadyExistsException;
import com.energy.api.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> getAddress(Long id) {
        return addressRepository.findById(id);
    }

    @Transactional
    public Optional<Address> createAddress(AddressDTO addressDTO) {
        Address address = new Address(addressDTO);

        return Optional.of(addressRepository.save(address));
    }

    @Transactional
    public Optional<Address> deleteAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new PersonAlreadyExistsException(""));
        addressRepository.deleteById(id);
        return Optional.ofNullable(address);
    }

}
