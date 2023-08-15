package com.energy.api.controller;

<<<<<<< HEAD
=======
import com.energy.api.entity.Address;
import com.energy.api.entity.AddressDTO;
>>>>>>> 8c86a07bf7eb7a0225ca11318b5ae43963298b73
import com.energy.api.entity.HomeAppliances;
import com.energy.api.entity.HomeAppliancesDTO;
import com.energy.api.exceptions.PersonAlreadyExistsException;
import com.energy.api.service.HomeApplianceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/home-appliance/")
public class HomeAApplianceController {

    @Autowired
    private HomeApplianceService homeApplianceService;

    @PostMapping("register")
    public ResponseEntity<?> registerAddress(@RequestBody @Valid HomeAppliancesDTO homeAppliancesDTO) {
        Optional<HomeAppliances> homeAppliances = homeApplianceService.createHomeAppliance(homeAppliancesDTO);

        if (homeAppliances.isEmpty()) {
            throw new PersonAlreadyExistsException("[Home Appliance] Ocorreu um problema ao cadastrar o eletrodoméstico.");
        }

        Map<String, String> response = new HashMap<>();
        response.put("[Home Appliance]", "Eletrodoméstico registrado com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
