package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.DayOff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayOffRepository extends JpaRepository<DayOff, Long> {


       List<DayOff> findAllByOrderByIdDesc();

}
