package com.api.othon.model.repository;

import com.api.othon.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {
    // Você pode definir métodos personalizados aqui, se necessário
}
