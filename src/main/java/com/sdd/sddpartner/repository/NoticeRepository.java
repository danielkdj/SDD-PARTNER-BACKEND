package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
//    @Query("SELECT o FROM Notice o INNER JOIN FETCH o.employees")
//    List<Notice> findAllFetchJoin();
}
