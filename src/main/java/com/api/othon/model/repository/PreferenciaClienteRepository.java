package com.api.othon.model.repository;

import com.api.othon.model.PreferenciaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciaClienteRepository extends JpaRepository<PreferenciaCliente, Long> {
}
