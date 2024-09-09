package com.api.othon.model.repository;

import com.api.othon.model.ProgramaFidelidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaFidelidadeRepository extends JpaRepository<ProgramaFidelidade, Long> {
}
