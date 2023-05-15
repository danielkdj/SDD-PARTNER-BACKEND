package com.sdd.sddpartner.common.repository;

import com.sdd.sddpartner.common.domain.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
	
}
