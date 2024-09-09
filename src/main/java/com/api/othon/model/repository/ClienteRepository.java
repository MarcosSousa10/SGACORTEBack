package com.api.othon.model.repository;

import com.api.othon.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Pode adicionar métodos de consulta personalizados, se necessário
}
