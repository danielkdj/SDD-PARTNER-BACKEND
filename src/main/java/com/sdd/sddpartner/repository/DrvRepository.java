package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Drv;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrvRepository extends JpaRepository<Drv, Long> {

    List<Drv> findByCar(Sort drvStart, String car);
}
