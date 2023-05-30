package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Ea;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UseRepository extends JpaRepository<Ea, Long> {

    List<Ea> findTop4ByCategoryItem_CategoryIdIn(Sort documentNo, List<Long> categoryId);
    List<Ea> findAllByCategoryItem_CategoryIdInAndApprovalStatus(Sort documentNo, List<Long> categoryId, Long approve);
    List<Ea> findAllByCategoryItem_CategoryIdIn(Sort documentNo, List<Long> categoryId);
    List<Ea> findByCategoryItem_CategoryIdInAndApprovalStatusIn(Sort documentNo, List<Long> categoryId, List<Long> approve);
    Long countByApprovalStatusAndCategoryItem_CategoryIdIn(Long approve, List<Long> categoryIdList);
    Ea findTopByCategoryItem_CategoryIdInAndApprovalStatus(Sort createdAt, List<Long> categoryIdList, Long approve);
}
