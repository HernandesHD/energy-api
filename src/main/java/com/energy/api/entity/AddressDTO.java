package com.energy.api.entity;

import jakarta.validation.constraints.*;

public record AddressDTO(
        @NotEmpty(message = "The { Road } field cannot be empty")
        @Size(max = 60, message = "The { Road } field size must be between 0 and 60")
        @Pattern(regexp = "[a-zA-Z]+", message = "The { Road } field must contain only letters")
        String road,
        @NotNull(message = "The { Number } field cannot be null")
        @Positive(message = "The { Number } field cannot be negative")
        Integer number,
        @NotEmpty(message = "The { District } field cannot be empty")
        String district,
        @NotEmpty(message = "The { City } field cannot be empty")
        String city
) {

}
