package com.energy.api.entity;

import com.energy.api.enums.PowerHomeAppliance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_home_appliances")
public class HomeAppliances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    private String brand;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PowerHomeAppliance volts; //110 or 220
    private String watts;

    public HomeAppliances(HomeAppliancesDTO homeAppliancesDTO) {
        this.name = homeAppliancesDTO.name();
        this.model = homeAppliancesDTO.model();
        this.brand = homeAppliancesDTO.brand();
        this.volts = homeAppliancesDTO.volts();
        this.watts = homeAppliancesDTO.watts();
    }
}
