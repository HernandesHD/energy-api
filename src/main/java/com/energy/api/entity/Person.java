package com.energy.api.entity;

import com.energy.api.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    @Column(name = "date_of_birth", nullable = false, columnDefinition = "date")
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String cpf;
    @OneToMany
    @JoinColumn(name = "person_id")
    private List<PersonRelation> kinship = new ArrayList<>();

    public Person(PersonDTO personDTO) {
        this.firstName = personDTO.firstName();
        this.lastName = personDTO.lastName();
        this.gender = personDTO.gender();
        this.dateOfBirth = personDTO.dateOfBirth();
        this.email = personDTO.email();
        this.cpf = personDTO.cpf();
        if (personDTO.kinship() != null) {
            for (PersonRelationDTO personRelationDTO : personDTO.kinship()) {
                PersonRelation personRelation = new PersonRelation(personRelationDTO);
                kinship.add(personRelation);
            }
        }
    }

}
