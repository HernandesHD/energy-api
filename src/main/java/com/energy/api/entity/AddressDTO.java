package com.energy.api.entity;

import jakarta.validation.constraints.*;

public record AddressDTO(
        @NotEmpty(message = "[Address] The { Road } field cannot be empty")
        @Size(max = 60, message = "[Address] The { Road } field size must be between 0 and 60")
        @Pattern(regexp = "[a-zA-Z]+", message = "[Address] The { Road } field must contain only letters")
        String road,
        @NotNull(message = "[Address] The { Number } field cannot be null")
        @Positive(message = "[Address] The { Number } field cannot be negative")
        Integer number,
        @NotEmpty(message = "[Address] The { District } field cannot be empty")
        String district,
        @NotEmpty(message = "[Address] The { City } field cannot be empty")
        String city
) {

}
