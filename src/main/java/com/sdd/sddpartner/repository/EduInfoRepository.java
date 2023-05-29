package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.EduInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EduInfoRepository extends JpaRepository<EduInfo, Long> {
}
