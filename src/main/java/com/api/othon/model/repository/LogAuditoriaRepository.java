package com.api.othon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.othon.model.LogAuditoria;

public interface LogAuditoriaRepository extends JpaRepository<LogAuditoria, Long> {
}
