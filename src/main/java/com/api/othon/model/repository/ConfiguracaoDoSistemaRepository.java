package com.api.othon.model.repository;

import com.api.othon.model.ConfiguracaoDoSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoDoSistemaRepository extends JpaRepository<ConfiguracaoDoSistema, Long> {
    ConfiguracaoDoSistema findByChaveConfiguracao(String chaveConfiguracao);
}
