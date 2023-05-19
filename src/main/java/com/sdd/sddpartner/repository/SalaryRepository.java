package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findAllByOrderByIdDesc();
}