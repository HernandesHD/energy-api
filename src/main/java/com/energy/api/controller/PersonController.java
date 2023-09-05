package com.energy.api.controller;

import com.energy.api.entity.PersonRelation;
import com.energy.api.entity.PersonRelationDTO;
import com.energy.api.enums.Gender;
import com.energy.api.exceptions.GenericErrorReponse;
import com.energy.api.exceptions.PersonAlreadyExistsException;
import com.energy.api.entity.Person;
import com.energy.api.entity.PersonDTO;
import com.energy.api.repository.PersonRepository;
import com.energy.api.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/v1/person/")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    private Map<String, String> response = new HashMap<>();

    @GetMapping("{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {
        Optional<Person> personDTO = personService.getPerson(id);
        if(personDTO.isEmpty()) {
            GenericErrorReponse<String> errorResponse = new GenericErrorReponse<>(
                    LocalDateTime.now(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ocorreu um erro.", "Usuário não localizado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
        return personDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("register")
    public ResponseEntity<?> registerPerson(@RequestBody @Valid PersonDTO personDTO) {
        Optional<Person> person = personService.createPerson(personDTO);
        if (person.isEmpty()) {
            throw new PersonAlreadyExistsException("[Person] O cpf " + personDTO.cpf() + " já está associado a um usuário");
        }
        response.put("[Person]", "Usuário registrado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*@PostMapping
    public ResponseEntity<?> registerPersonRelation(@RequestBody @Valid PersonRelationDTO personRelationDTOs) {
        Optional<PersonRelation> personRelation = personService.savePersonRelations(personRelationDTOs)
    }*/

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editPerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson == null) {
            GenericErrorReponse<String> errorResponse =  new GenericErrorReponse<>(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND,
                    "Ocorreu um erro ao buscar o usuário.",
                    "Pessoa não foi encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        //Atualiza dados Person
        Person person = existingPerson.get();
        person.setFirstName(personDTO.firstName());
        person.setLastName(personDTO.lastName());
        person.setGender(personDTO.gender());
        person.setDateOfBirth(personDTO.dateOfBirth());
        person.setEmail(personDTO.email());
        person.setCpf(personDTO.cpf());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PersonDTO personDTOAuxiliar = objectMapper.convertValue(person, PersonDTO.class);
        personService.createPerson(personDTOAuxiliar);
        response.put("[Person]", "Usuário editado com sucesso.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        Optional<Person> person = personService.deletePerson(id);
        if(person.isEmpty()) {
            throw new PersonAlreadyExistsException("[Person] O usuário de ID: " + id + " não foi localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
