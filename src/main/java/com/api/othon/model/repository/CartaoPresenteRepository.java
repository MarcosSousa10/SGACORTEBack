package com.api.othon.model.repository;

import com.api.othon.model.CartaoPresente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoPresenteRepository extends JpaRepository<CartaoPresente, Long> {
    CartaoPresente findByCodigo(String codigo);
}
