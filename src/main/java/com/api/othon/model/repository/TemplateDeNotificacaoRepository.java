package com.api.othon.model.repository;

import com.api.othon.model.TemplateDeNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateDeNotificacaoRepository extends JpaRepository<TemplateDeNotificacao, Long> {
}
