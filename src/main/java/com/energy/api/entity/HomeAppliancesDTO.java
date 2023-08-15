package com.energy.api.entity;

import com.energy.api.enums.PowerHomeAppliance;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record HomeAppliancesDTO(
        @NotEmpty(message = "The name field cannot be empty.")
        @Size(min = 2, max = 25, message = "The name field size must be between {min} and {max}.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "The name field must contain only letters.")
        String name,
        @NotEmpty(message = "The model field cannot be empty.")
        @Size(min = 2, max = 25, message = "The model field size must be between {min} and {max}.")
        String model,
        @NotEmpty(message = "The brand field cannot be empty.")
        @Size(min = 2, max = 25, message = "The brand field size must be between {min} and {max}.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "The brand field must contain only letters.")
        String brand,
        @NotNull(message = "The power home appliance field cannot be empty.")
        PowerHomeAppliance volts,
        @NotEmpty(message = "The watts field cannot be empty.")
        @Size(min = 2, max = 10, message = "The watts field size must be between {min} and {max}.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "The watts field must contain only letters.")
        String watts
        ) {

}
