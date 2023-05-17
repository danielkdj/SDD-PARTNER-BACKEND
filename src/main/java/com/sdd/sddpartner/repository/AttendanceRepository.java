package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Attendance;
import com.sdd.sddpartner.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
