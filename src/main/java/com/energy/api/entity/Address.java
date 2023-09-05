package com.energy.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_address")
@Table(name = "_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "public_place")
    private String public_place;

    private String road;
    private Integer number;
    private String district;
    private String city;

    //@ManyToOne
    //@JoinColumn(name = "person_id")
    //private Person person;

    public Address(AddressDTO addressDTO) {
        this.cep = addressDTO.cep();
        this.public_place = addressDTO.public_place();
        this.road = addressDTO.road();
        this.number = addressDTO.number();
        this.district = addressDTO.district();
        this.city = addressDTO.city();
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "Endereço{" +
                "rua='" + this.road + '\'' +
                ", número=" + this.number +
                ", complemento='" + this.public_place + '\'' +
                ", bairro='" + this.district + '\'' +
                ", cidade='" + this.city + '\'' +
                ", cep='" + this.cep + '\'' +
                '}';
    }


}
