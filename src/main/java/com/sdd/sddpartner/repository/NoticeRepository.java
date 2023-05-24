package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.domain.NoticeDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findTop3By(Sort noticeNo);
    List<Notice> findByTitleContaining(Sort noticeNo, String title);
}
