package com.energy.api.entity;

import com.energy.api.enums.PowerHomeAppliance;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record HomeAppliancesDTO(
        @NotEmpty(message = "[HomeAppliance] The name field cannot be empty.")
        @Size(min = 2, max = 25, message = "[HomeAppliance] The name field size must be between {min} and {max}.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "[HomeAppliance] The name field must contain only letters.")
        String name,
        @NotEmpty(message = "[HomeAppliance] The model field cannot be empty.")
        @Size(min = 2, max = 25, message = "[HomeAppliance] The model field size must be between {min} and {max}.")
        String model,
        @NotEmpty(message = "[HomeAppliance] The brand field cannot be empty.")
        @Size(min = 2, max = 25, message = "[HomeAppliance] The brand field size must be between {min} and {max}.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "[HomeAppliance] The brand field must contain only letters.")
        String brand,
        @NotNull(message = "[HomeAppliance] The power home appliance field cannot be empty.")
        PowerHomeAppliance volts,
        @NotEmpty(message = "[HomeAppliance] The watts field cannot be empty.")
        @Size(min = 2, max = 10, message = "[HomeAppliance] The watts field size must be between {min} and {max}.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "[HomeAppliance] The watts field must contain only letters.")
        String watts
        ) {

}
