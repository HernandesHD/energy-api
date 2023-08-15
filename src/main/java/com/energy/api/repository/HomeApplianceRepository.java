package com.energy.api.repository;

import com.energy.api.entity.HomeAppliances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeApplianceRepository extends JpaRepository<HomeAppliances, Long> {
}
