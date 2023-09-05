package com.energy.api.service;

import com.energy.api.entity.Address;
import com.energy.api.entity.AddressDTO;
import com.energy.api.entity.PersonRelation;
import com.energy.api.entity.PersonRelationDTO;
import com.energy.api.exceptions.PersonAlreadyExistsException;
import com.energy.api.repository.AddressRepository;
import com.energy.api.repository.PersonRelationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonRelationService {

    private final PersonRelationRepository personRelationRepository;

    public PersonRelationService(PersonRelationRepository personRelationRepository) {
        this.personRelationRepository = personRelationRepository;
    }

    public Optional<PersonRelation> getPersonRelation(Long id) {
        return personRelationRepository.findById(id);
    }

    @Transactional
    public Optional<PersonRelation> createPersonRelation(PersonRelationDTO personRelationDTO) {
        PersonRelation personRelation = new PersonRelation(personRelationDTO);
        return Optional.of(personRelationRepository.save(personRelation));
    }

    @Transactional
    public Optional<PersonRelation> deletePersonRelation(Long id) {
        PersonRelation personRelation = personRelationRepository.findById(id).orElseThrow(() -> new PersonAlreadyExistsException(""));
        personRelationRepository.deleteById(id);
        return Optional.ofNullable(personRelation);
    }

}
