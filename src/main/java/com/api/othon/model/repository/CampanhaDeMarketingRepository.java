package com.api.othon.model.repository;

import com.api.othon.model.CampanhaDeMarketing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaDeMarketingRepository extends JpaRepository<CampanhaDeMarketing, Long> {
}
