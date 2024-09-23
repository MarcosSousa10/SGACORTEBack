package com.api.othon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.othon.model.VendaDTO;
import com.api.othon.model.Vendas;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VendasRepository extends JpaRepository<Vendas, Long> {
    @Query("SELECT new com.api.othon.model.VendaDTO(v.id, v.dataVenda, v.valorTotal, vi.inventario.id, vi.quantidade, v.metodoPagamento, c, p, f) " +
    "FROM Vendas v " +
    "LEFT JOIN v.vendaItems vi " +
    "LEFT JOIN v.cliente c " +
    "LEFT JOIN v.profissional p " +
    "LEFT JOIN v.filial f")
List<VendaDTO> findAllWithItens();

@Query("SELECT new com.api.othon.model.VendaDTO(v.id, v.dataVenda, v.valorTotal, vi.id, vi.quantidade, v.metodoPagamento, c, p, f) " + 
    "FROM Vendas v " +
    "LEFT JOIN v.vendaItems vi " +
    "LEFT JOIN v.cliente c " +
    "LEFT JOIN v.profissional p " +
    "LEFT JOIN v.filial f " +
    "WHERE v.id = :vendaId " +
    "GROUP BY v.id, v.dataVenda, v.valorTotal, vi.id, vi.quantidade, v.metodoPagamento, c.id, p.id, f.id")
Optional<VendaDTO> findAllWithItensById(@Param("vendaId") Long vendaId);


    @Query("SELECT v FROM Vendas v WHERE DATE(v.dataVenda) = DATE(:today)")
    List<Vendas> findVendasByDataVenda(@Param("today") LocalDate today);
}
