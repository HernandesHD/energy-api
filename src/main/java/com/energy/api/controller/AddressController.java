package com.energy.api.controller;

import com.energy.api.entity.Address;
import com.energy.api.entity.AddressDTO;
<<<<<<< HEAD
=======
import com.energy.api.entity.Person;
>>>>>>> 8c86a07bf7eb7a0225ca11318b5ae43963298b73
import com.energy.api.exceptions.PersonAlreadyExistsException;
import com.energy.api.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address/")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("register")
    public ResponseEntity<?> registerAddress(@RequestBody @Valid AddressDTO addressDTO) {
        Optional<Address> address = addressService.createAddress(addressDTO);

        if (address.isEmpty()) {
            throw new PersonAlreadyExistsException("[Address] Ocorreu um problema ao cadastrar o endereço.");
        }

        Map<String, String> response = new HashMap<>();
        response.put("[Address]", "Endereço registrado com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
