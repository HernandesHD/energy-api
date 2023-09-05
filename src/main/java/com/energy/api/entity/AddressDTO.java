package com.energy.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.util.List;

public record AddressDTO(

        @JsonIgnore
        String id,
        @NotNull(message = "The { Number } field cannot be null")
        @Positive(message = "The { Number } field cannot be negative")
        String cep,
        @NotEmpty(message = "The { public_place } field cannot be empty")
        @Size(max = 60, message = "The { public_place } field size must be between 0 and 60")
        @Pattern(regexp = "[a-zA-Z]+", message = "The { public_place } field must contain only letters")
        String public_place,

        @NotEmpty(message = "The { Road } field cannot be empty")
        @Size(max = 60, message = "The { Road } field size must be between 0 and 60")
        @Pattern(regexp = "[a-zA-Z]+", message = "The { Road } field must contain only letters")
        String road,
        @NotNull(message = "The { Number } field cannot be null")
        @Positive(message = "The { Number } field cannot be negative")
        Integer number,
        @NotEmpty(message = "The { District } field cannot be empty")
        String district,
        @NotEmpty(message = "[Address] The { City } field cannot be empty")
        String city,

        //@JsonIgnore
        //Person person,

        @NotNull
        @Positive(message = "The { Number } field cannot be negative")
        Long personId
) {

}
