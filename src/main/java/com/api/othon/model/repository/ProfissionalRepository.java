package com.api.othon.model.repository;

import com.api.othon.model.Profissional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Optional<Profissional> findByNome(String nome);

}
