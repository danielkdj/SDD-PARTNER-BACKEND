package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Ea;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UseRepository extends JpaRepository<Ea, Long> {
    List<Ea> findByDocumentNoAndEaStartGreaterThanEqualAndEaEndLessThanEqual(Sort documentNo, String category, LocalDateTime start, LocalDateTime end);

    List<Ea> findAllByCategory(Sort documentNo, long category);

    /*
    메소드명이 길면 JpaRepository에서 인식 안될 가능성이 있어서 임시작성해 둠
    @Query("SELECT e FROM Eacation e WHERE e.eduId = :eduId AND e.eduStart >= :eduStart AND e.eduEnd <= :eduEnd")
    List<Eacation> findByEaIdAndEaStartGreaterThanEqualAndEaEndLessThanEqual(@Param("eduId") Long eduId, @Param("eduStart") Date eduStart, @Param("eduEnd") Date eduEnd);
    */
}
