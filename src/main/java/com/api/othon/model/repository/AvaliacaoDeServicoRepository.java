package com.api.othon.model.repository;

import com.api.othon.model.AvaliacaoDeServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoDeServicoRepository extends JpaRepository<AvaliacaoDeServico, Long> {
}
