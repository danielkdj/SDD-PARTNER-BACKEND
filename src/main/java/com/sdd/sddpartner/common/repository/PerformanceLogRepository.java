package com.sdd.sddpartner.common.repository;

import com.sdd.sddpartner.common.domain.PerformanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceLogRepository extends JpaRepository<PerformanceLog, Long> {
	
}
