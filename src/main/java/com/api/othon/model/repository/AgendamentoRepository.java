package com.api.othon.model.repository;

import com.api.othon.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.api.othon.model.Agendamento.Status;
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("SELECT a FROM Agendamento a WHERE LOWER(a.cliente.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Agendamento> findByClienteNomeContainingIgnoreCase(@Param("nome") String nome);
    List<Agendamento> findByStatus(Status status);

}
