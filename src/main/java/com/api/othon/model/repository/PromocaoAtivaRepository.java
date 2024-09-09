package com.api.othon.model.repository;

import com.api.othon.model.PromocaoAtiva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocaoAtivaRepository extends JpaRepository<PromocaoAtiva, Long> {
    List<PromocaoAtiva> findByFilial_FilialId(Long filialId);
}
