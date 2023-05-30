package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Counseling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("counselingRepository")
public interface CounselingRepository extends JpaRepository<Counseling, Long>{

	List<Counseling> findAllByOrderByCounIdDesc();

	@Query("SELECT c FROM Counseling c WHERE LOWER(c.counTitle) LIKE LOWER(CONCAT('%', :counTitle, '%'))")
	List<Counseling> findByTitle(@Param("counTitle") String counTitle);
}
