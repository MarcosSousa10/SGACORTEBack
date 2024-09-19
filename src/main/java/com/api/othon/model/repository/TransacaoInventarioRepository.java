package com.api.othon.model.repository;

import com.api.othon.model.TransacaoInventario;
import com.api.othon.model.TransacaoInventarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoInventarioRepository extends JpaRepository<TransacaoInventario, TransacaoInventarioId> {
}
