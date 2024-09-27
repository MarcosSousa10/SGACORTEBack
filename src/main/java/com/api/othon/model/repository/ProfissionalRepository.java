package com.api.othon.model.repository;

import com.api.othon.controller.dto.ProfissionalDTO;
import com.api.othon.model.Profissional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Optional<Profissional> findByNome(String nome);

    @Query("SELECT new  com.api.othon.controller.dto.ProfissionalDTO(p.id, p.nome, p.imagem) FROM Profissional p")
    List<ProfissionalDTO> findAllProfissionalDTOs();
    @Query("SELECT p FROM Profissional p JOIN FETCH p.imagem")
    List<Profissional> findAllWithImages();
    
}

