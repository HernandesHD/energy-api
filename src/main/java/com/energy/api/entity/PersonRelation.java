package com.energy.api.entity;

import com.energy.api.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "person_relation")
public class PersonRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String relationshipType;
    @Column(name = "pr_first_name", nullable = false)
    private String prFirstName;
    @Column(name = "pr_last_name", nullable = false)
    private String prLastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "pr_gender", nullable = false)
    private Gender prGender;
    @Column(name = "pr_date_of_birth", nullable = false, columnDefinition = "date")
    private LocalDate prDateOfBirth;
    @Column(name = "pr_email", nullable = false)
    private String prEmail;
    @Column(name = "pr_cpf", unique = true, nullable = false)
    private String prCpf;

    public PersonRelation(PersonRelationDTO personRelationDTO) {
        this.relationshipType = personRelationDTO.relationshipType();
        this.prFirstName = personRelationDTO.prFirstName();
        this.prLastName = personRelationDTO.prLastName();
        this.prGender = personRelationDTO.prGender();
        this.prDateOfBirth = personRelationDTO.prDateOfBirth();
        this.prEmail = personRelationDTO.prEmail();
        this.prCpf = personRelationDTO.prCpf();
    }
}
