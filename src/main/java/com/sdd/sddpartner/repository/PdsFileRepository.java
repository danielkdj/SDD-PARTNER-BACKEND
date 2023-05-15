package com.sdd.sddpartner.repository;

import java.util.List;

import com.sdd.sddpartner.domain.PdsFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdsFileRepository extends JpaRepository<PdsFile, Long> {
	
	public List<PdsFile> findByFullName(String fullName);
	
}
