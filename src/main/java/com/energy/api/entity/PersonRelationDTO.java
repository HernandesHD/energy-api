package com.energy.api.entity;

import com.energy.api.enums.Gender;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PersonRelationDTO(
        @Size(min = 3, max = 50, message = "The first name relation field size must be between {min} and {max}.")
        String relationshipType,

        @NotEmpty(message = "The first name field cannot be empty.")
        @Size(min = 2, max = 25, message = "The first name field size must be between {min} and {max}.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "The first name field must contain only letters.")
        String prFirstName,
        @NotEmpty(message = "The last name field cannot be empty.")
        @Size(max = 25, message = "The last name field size must be between 0 and 25.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "The last name field must contain only letters.")
        String prLastName,
        //@JsonFormat(shape = JsonFormat.Shape.STRING)
        @NotNull(message = "The gender field cannot be empty.")
        Gender prGender,

        @Past(message = "The date of birth in date of birth field must be before the current date.")
        @DateTimeFormat(style = "MM-dd-yyyy")
        LocalDate prDateOfBirth,
        @NotBlank(message = "The email field cannot be empty.")
        @Email(message = "The email is invalid.")
        String prEmail,
        @NotBlank(message = "The cpf field cannot be empty.")
        @Size(min = 11, max = 11, message = "The cpf field must have 11 characters.")
        @CPF(message = "The CPF is invalid.")
        String prCpf
) {
}
