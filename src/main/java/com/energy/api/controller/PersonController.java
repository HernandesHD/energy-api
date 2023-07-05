package com.energy.api.controller;

import com.energy.api.exceptions.GenericErrorReponse;
import com.energy.api.exceptions.PersonAlreadyExistsException;
import com.energy.api.entity.Person;
import com.energy.api.entity.PersonDTO;
import com.energy.api.service.PersonService;
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
@RequestMapping("/api/v1/person/")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {
        Optional<Person> personDTO = personService.getPerson(id);
        if(personDTO.isEmpty()) {
            GenericErrorReponse<String> errorResponse = new GenericErrorReponse<>(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro.", "Usuário não localizado");
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

        Map<String, String> response = new HashMap<>();
        response.put("[Person]", "Usuário registrado com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
