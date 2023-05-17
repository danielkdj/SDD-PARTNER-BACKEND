package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Edu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EduRepository extends JpaRepository<Edu, Long> {
    List<Edu> findByEduIdAndEduStartGreaterThanEqualAndEduEndLessThanEqual(Sort eduNo, String category, LocalDateTime start, LocalDateTime end);

    /*
    메소드명이 길면 JpaRepository에서 인식 안될 가능성이 있어서 임시작성해 둠
    @Query("SELECT e FROM Education e WHERE e.eduId = :eduId AND e.eduStart >= :eduStart AND e.eduEnd <= :eduEnd")
    List<Education> findByEduIdAndEduStartGreaterThanEqualAndEduEndLessThanEqual(@Param("eduId") Long eduId, @Param("eduStart") Date eduStart, @Param("eduEnd") Date eduEnd);
    */
}
