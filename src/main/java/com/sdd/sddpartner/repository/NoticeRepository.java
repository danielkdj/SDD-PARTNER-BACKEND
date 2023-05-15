package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
	
}
