package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Completion;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComRepository extends JpaRepository<Completion, Long> {
    List<Completion> findAllBySearch(Sort documentNo, long category, long years, long quarters, char completion);
}
