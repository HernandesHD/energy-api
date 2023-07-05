package com.energy.api.repository;

import com.energy.api.entity.PersonRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRelationRepository extends JpaRepository<PersonRelation, String> {
    Optional<PersonRelation> findByPrCpf(String cpf);
    boolean existsByPrCpf(String cpf);
}
