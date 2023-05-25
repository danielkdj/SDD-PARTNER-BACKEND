package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Ea;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UseRepository extends JpaRepository<Ea, Long> {

    List<Ea> findTop4ByCategoryIdIn(Sort documentNo, List<Long> categoryId);
    //List<Ea> findTop4ByCategoryIdInAndApprovalStageAndSecondApproval(Sort documentNo, List<Long> categoryId, Long approvalStage, String secondApproval);
    //List<Ea> findTop4ByApprovalStageAndSecondApprovalAndCategoryId(Sort documentNo, Long approvalStage, String secondApproval, Long categoryId);
    List<Ea> findAllByApprovalStageAndSecondApprovalAndCategoryId(Sort documentNo, Long approvalStage, String secondApproval, Long categoryId);
    List<Ea> findAllByCategoryIdAndCompletedAtNotNull(Sort documentNo, Long categoryId);
    List<Ea> findAllByCategoryId(Sort documentNo, Long categoryId);
    List<Ea> findByTitleContaining(Sort documentNo, String title);
}
