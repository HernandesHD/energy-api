package com.energy.api.controller;

import com.energy.api.entity.Person;
import com.energy.api.entity.PersonDTO;
import com.energy.api.entity.PersonRelation;
import com.energy.api.entity.PersonRelationDTO;
import com.energy.api.exceptions.GenericErrorReponse;
import com.energy.api.exceptions.PersonAlreadyExistsException;
import com.energy.api.repository.PersonRelationRepository;
import com.energy.api.repository.PersonRepository;
import com.energy.api.service.PersonRelationService;
import com.energy.api.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person-relation/")
public class PersonRelationController {

    @Autowired
    private PersonRelationService personRelationService;
    @Autowired
    private PersonRelationRepository personRelationRepository;
    private Map<String, String> response = new HashMap<>();


    @GetMapping("{id}")
    public ResponseEntity<?> getPersonRelationById(@PathVariable Long id) {
        Optional<PersonRelation> personRelationDTO = personRelationService.getPersonRelation(id);
        if(personRelationDTO.isEmpty()) {
            GenericErrorReponse<String> errorResponse = new GenericErrorReponse<>(
                    LocalDateTime.now(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ocorreu um erro.", "Person Relation não localizado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
        return personRelationDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("register")
    public ResponseEntity<?> registerPersonRelation(@RequestBody @Valid PersonRelationDTO personRelationDTO) {
        Optional<PersonRelation> personRelation = personRelationService.createPersonRelation(personRelationDTO);
        if (personRelation.isEmpty()) {
            throw new PersonAlreadyExistsException("[Person] O cpf " + personRelation.get().getPrCpf() + " já está associado a um usuário");
        }
        response.put("[PersonRelation]", "Usuário registrado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editPerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        Optional<PersonRelation> existingPersonRelation = personRelationRepository.findById(id);
        if (existingPersonRelation == null) {
            GenericErrorReponse<String> errorResponse =  new GenericErrorReponse<>(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND,
                    "Ocorreu um erro ao buscar o usuário.",
                    "Pessoa não foi encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        //Atualiza dados Person
        PersonRelation personRelation = existingPersonRelation.get();
        personRelation.setPrFirstName(personDTO.firstName());
        personRelation.setPrLastName(personDTO.lastName());
        personRelation.setPrGender(personDTO.gender());
        personRelation.setPrDateOfBirth(personDTO.dateOfBirth());
        personRelation.setPrEmail(personDTO.email());
        personRelation.setPrCpf(personDTO.cpf());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PersonRelationDTO personRelationDTODTOAuxiliar = objectMapper.convertValue(personRelation, PersonRelationDTO.class);
        personRelationService.createPersonRelation(personRelationDTODTOAuxiliar);
        response.put("[PersonRelation]", "Usuário editado com sucesso.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        Optional<PersonRelation> personRelation = personRelationService.deletePersonRelation(id);
        if(personRelation.isEmpty()) {
            throw new PersonAlreadyExistsException("[PersonRelation] O usuário de ID: " + id + " não foi localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
