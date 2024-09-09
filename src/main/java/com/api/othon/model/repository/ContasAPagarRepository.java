package com.api.othon.model.repository;

import com.api.othon.model.ContasAPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasAPagarRepository extends JpaRepository<ContasAPagar, Long> {
}
