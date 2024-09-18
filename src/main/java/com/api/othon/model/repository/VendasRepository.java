package com.api.othon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.othon.model.Vendas;

import java.time.LocalDate;
import java.util.List;

public interface VendasRepository extends JpaRepository<Vendas, Long> {

    @Query("SELECT v FROM Vendas v WHERE DATE(v.dataVenda) = DATE(:today)")
    List<Vendas> findVendasByDataVenda(@Param("today") LocalDate today);
}
