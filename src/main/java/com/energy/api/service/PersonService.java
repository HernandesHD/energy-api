package com.energy.api.service;

import com.energy.api.entity.Person;
import com.energy.api.entity.PersonDTO;
import com.energy.api.entity.PersonRelation;
import com.energy.api.entity.PersonRelationDTO;
import com.energy.api.repository.PersonRelationRepository;
import com.energy.api.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonRelationRepository personRelationRepository;

    public PersonService(PersonRepository personRepository, PersonRelationRepository personRelationRepository) {
        this.personRepository = personRepository;
        this.personRelationRepository = personRelationRepository;
    }

    public Optional<Person> getPerson(Long id) {
        //return personRepository.findByCpf(cpf);
        return personRepository.findById(id);
    }

    @Transactional
    public Optional<Person> createPerson(PersonDTO personDTO) {
        if (personRepository.existsByCpf(personDTO.cpf())) {
            return Optional.empty();
        }

        Person person = new Person(personDTO);
        List<PersonRelation> kinship = savePersonRelations(personDTO.kinship());
        person.setKinship(kinship);
        return Optional.of(personRepository.save(person));
    }

    private List<PersonRelation> savePersonRelations(List<PersonRelationDTO> personRelationDTOs) {
        List<PersonRelation> personRelations = new ArrayList<>();
        for (PersonRelationDTO personRelationDTO : personRelationDTOs) {
            PersonRelation personRelation = new PersonRelation(personRelationDTO);
            personRelation = personRelationRepository.save(personRelation);
            personRelations.add(personRelation);
        }
        return personRelations;
    }

}
