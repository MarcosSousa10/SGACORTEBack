package com.api.othon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.othon.model.Comissao;

@Repository
public interface ComissaoRepository extends JpaRepository<Comissao, Long> {

}
