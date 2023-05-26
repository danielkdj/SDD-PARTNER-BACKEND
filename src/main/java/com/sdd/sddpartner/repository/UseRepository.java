package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Ea;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UseRepository extends JpaRepository<Ea, Long> {

    List<Ea> findTop4ByCategoryItem_CategoryIdIn(Sort documentNo, List<Long> categoryId);
    List<Ea> findAllByCategoryItem_CategoryIdInAndApprovalStatus(Sort documentNo, List<Long> categoryId, Long approve);
    List<Ea> findAllByCategoryItem_CategoryIdIn(Sort documentNo, List<Long> categoryId);
    List<Ea> findByCategoryItem_CategoryIdInAndApprovalStatusIn(Sort documentNo, List<Long> categoryId, List<Long> approve);
    List<Ea> findByCategoryItem_CategoryIdInAndApprovalStatusInAndTitleContaining(Sort documentNo, List<Long> categoryId, List<Long> approve, String title);
}
