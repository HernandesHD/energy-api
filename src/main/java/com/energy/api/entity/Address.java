package com.energy.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String road;
    private Integer number;
    private String district;
    private String city;

    public Address(AddressDTO addressDTO) {
        this.road = addressDTO.road();
        this.number = addressDTO.number();
        this.district = addressDTO.district();
        this.city = addressDTO.city();
    }


}
