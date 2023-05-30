package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findAllByEmpId(String empId);
}
