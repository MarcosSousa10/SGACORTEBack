package com.api.othon.model.repository;

import com.api.othon.model.DesempenhoDosFuncionarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesempenhoDosFuncionariosRepository extends JpaRepository<DesempenhoDosFuncionarios, Long> {
}
