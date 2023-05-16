package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Notice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByTitleContains(Sort noticeNo, String keyword);
}
