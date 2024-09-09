package com.api.othon.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.api.othon.model.Produto;

public interface Repository extends CrudRepository<Produto, Long> {
    @Query(value = "select * from Produto", nativeQuery = true)
    Optional<Produto> findAll(String codigo);
    @Query(value = "select * from Produto", nativeQuery = true)
    List<Produto> findAllCodProdutos(String codigo);
    @Query(value = "select * from Produto", nativeQuery = true)
    Optional<Produto> find(String codigo);
    @Query(value = "select * from Produto", nativeQuery = true)
    List<Produto> findAllCod(String codigo);
}
