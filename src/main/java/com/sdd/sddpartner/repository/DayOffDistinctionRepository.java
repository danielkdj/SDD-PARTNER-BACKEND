package com.sdd.sddpartner.repository;


import com.sdd.sddpartner.domain.DayOffDistinction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DayOffDistinctionRepository extends JpaRepository<DayOffDistinction, Long> {
//    List<DayOffDistinction> findAllByOrderByOffCodeDesc();
}