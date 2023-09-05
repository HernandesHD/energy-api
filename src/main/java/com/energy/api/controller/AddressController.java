package com.energy.api.controller;

import com.energy.api.entity.Address;
import com.energy.api.entity.AddressDTO;
import com.energy.api.entity.Person;
import com.energy.api.exceptions.GenericErrorReponse;
import com.energy.api.exceptions.PersonAlreadyExistsException;
import com.energy.api.service.AddressService;
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
@RequestMapping("/api/v1/address/")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private PersonService personService;

    Map<String, String> response = new HashMap<>();

    @GetMapping("{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {
        Optional<Address> addressDTO = addressService.getAddress(id);
        if(addressDTO.isEmpty()) {
            GenericErrorReponse<String> errorResponse = new GenericErrorReponse<>(
                    LocalDateTime.now(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ocorreu um erro.", "Endereço não localizado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
        return addressDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("register")
    public ResponseEntity<?> registerAddress(@RequestBody @Valid AddressDTO addressDTO) {
        Optional<Address> address = addressService.createAddress(addressDTO);

        if (address.isEmpty()) {
            throw new PersonAlreadyExistsException("[Address] Ocorreu um problema ao cadastrar o endereço.");
        }

        response.put("[Address]", "Endereço registrado com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        Optional<Address> address = addressService.deleteAddress(id);
        if(address.isEmpty()) {
            throw new PersonAlreadyExistsException("[Address] O usuário de ID: " + id + " não foi localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
