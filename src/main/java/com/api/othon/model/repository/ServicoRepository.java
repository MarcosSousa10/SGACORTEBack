package com.api.othon.model.repository;

import com.api.othon.model.Servico;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Optional<Servico> findByNome(String nome);

    @Query("SELECT p FROM Servico p JOIN FETCH p.imagem")
    List<Servico> findAllWithImages();
}
