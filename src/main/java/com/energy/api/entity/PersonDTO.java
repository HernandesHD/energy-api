package com.energy.api.entity;

import com.energy.api.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record PersonDTO(
<<<<<<< HEAD
        @NotEmpty(message = "The first name field cannot be empty.")
        @Size(min = 2, max = 25, message = "The first name field size must be between {min} and {max}.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "The first name field must contain only letters.")
        String firstName,
        @NotEmpty(message = "The last name field cannot be empty.")
        @Size(max = 25, message = "The last name field size must be between 0 and 25.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "The last name field must contain only letters.")
        String lastName,
        //@JsonFormat(shape = JsonFormat.Shape.STRING)
        @NotNull(message = "The gender field cannot be empty.")
        Gender gender,

        @NotBlank(message = "The email field cannot be empty.")
        @Email(message = "The email is invalid.")
        String email,

        @NotBlank(message = "The cpf field cannot be empty.")
        @Size(min = 11, max = 11, message = "The cpf field must have 11 characters.")
        @CPF(message = "The CPF is invalid.")
        String cpf,

        //@NotNull(message = "[Person] The date of birth  field cannot be empty.")
        @Past(message = "The date of birth in date of birth field must be before the current date.")
=======
        @NotEmpty(message = "[Person] The first name field cannot be empty.")
        @Size(min = 2, max = 25, message = "[Person] The first name field size must be between {min} and {max}.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "[Person] The first name field must contain only letters.")
        String firstName,
        @NotEmpty(message = "[Person] The last name field cannot be empty.")
        @Size(max = 25, message = "[Person] The last name field size must be between 0 and 25.")
        @Pattern(regexp = "[a-zA-ZÀ-ú]+", message = "[Person] The last name field must contain only letters.")
        String lastName,
        //@JsonFormat(shape = JsonFormat.Shape.STRING)
        @NotNull(message = "[Person] The gender field cannot be empty.")
        Gender gender,

        @NotBlank(message = "[Person] The email field cannot be empty.")
        @Email(message = "[Person] The email is invalid.")
        String email,

        @NotBlank(message = "[Person] The cpf field cannot be empty.")
        @Size(min = 11, max = 11, message = "[Person] The cpf field must have 11 characters.")
        @CPF(message = "[Person] The CPF is invalid.")
        String cpf,

        //@NotNull(message = "[Person] The date of birth  field cannot be empty.")
        @Past(message = "[Person] The date of birth in date of birth field must be before the current date.")
>>>>>>> 8c86a07bf7eb7a0225ca11318b5ae43963298b73
        @DateTimeFormat(style = "yyyy-MM-dd")
        LocalDate dateOfBirth,
        @Valid
        List<PersonRelationDTO> kinship
) {
}
