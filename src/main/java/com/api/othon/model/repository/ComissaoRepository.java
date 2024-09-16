package com.api.othon.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.othon.model.Comissao;

@Repository
public interface ComissaoRepository extends JpaRepository<Comissao, Long> {
    List<Comissao> findByProfissionalId(Long profissionalId);

}
