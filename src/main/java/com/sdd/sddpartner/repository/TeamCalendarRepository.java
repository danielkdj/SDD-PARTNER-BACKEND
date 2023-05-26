package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Board;
import com.sdd.sddpartner.domain.TeamCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamCalendarRepository extends JpaRepository<TeamCalendar, String> {
    public List<TeamCalendar> readTeamCalendarsByDepartmentNumber(int departmentNumber);

}
