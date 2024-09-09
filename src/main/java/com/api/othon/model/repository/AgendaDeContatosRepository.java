package com.api.othon.model.repository;

import com.api.othon.model.AgendaDeContatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaDeContatosRepository extends JpaRepository<AgendaDeContatos, Long> {
}
