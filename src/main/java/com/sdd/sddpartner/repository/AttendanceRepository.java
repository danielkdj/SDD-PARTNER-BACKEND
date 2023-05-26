package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
