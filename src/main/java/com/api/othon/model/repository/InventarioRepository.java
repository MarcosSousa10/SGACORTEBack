package com.api.othon.model.repository;

import com.api.othon.model.Agendamento;
import com.api.othon.model.Cliente;
import com.api.othon.model.Inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    
    @Query("SELECT i FROM Inventario i WHERE LOWER(i.nomeProduto) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Inventario> findByInventarioNomeProdutoContainingIgnoreCase(@Param("nome") String nome);

}
