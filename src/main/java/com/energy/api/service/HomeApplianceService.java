package com.energy.api.service;

import com.energy.api.entity.Address;
import com.energy.api.entity.HomeAppliances;
import com.energy.api.entity.HomeAppliancesDTO;
import com.energy.api.entity.PersonRelation;
import com.energy.api.exceptions.PersonAlreadyExistsException;
import com.energy.api.repository.HomeApplianceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeApplianceService {

    private final HomeApplianceRepository homeApplianceRepository;

    public HomeApplianceService(HomeApplianceRepository homeApplianceRepository) {
        this.homeApplianceRepository = homeApplianceRepository;
    }

    public Optional<HomeAppliances> getHomeAppliances(Long id) {
        return homeApplianceRepository.findById(id);
    }

    @Transactional
    public Optional<HomeAppliances> createHomeAppliance(HomeAppliancesDTO homeAppliancesDTO) {
        HomeAppliances homeAppliancesDTO1 = new HomeAppliances(homeAppliancesDTO);

        return Optional.of(homeApplianceRepository.save(homeAppliancesDTO1));
    }

    @Transactional
    public Optional<HomeAppliances> deleteHomeAppliances(Long id) {
        HomeAppliances homeAppliances = homeApplianceRepository.findById(id).orElseThrow(() -> new PersonAlreadyExistsException(""));
        homeApplianceRepository.deleteById(id);
        return Optional.ofNullable(homeAppliances);
    }

}
