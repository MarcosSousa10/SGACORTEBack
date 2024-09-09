package com.api.othon.model.repository;

import com.api.othon.model.ComunicacaoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicacaoClienteRepository extends JpaRepository<ComunicacaoCliente, Long> {
}
