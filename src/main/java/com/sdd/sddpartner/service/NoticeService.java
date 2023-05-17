package com.sdd.sddpartner.service;

import java.util.List;
import com.sdd.sddpartner.domain.Notice;

public interface NoticeService {

	void create(Notice notice) throws Exception;

	Notice select(Long noticeNo) throws Exception;

	void update(Notice notice) throws Exception;

	void delete(Long noticeNo) throws Exception;

	List<Notice> searchList(String keyword) throws Exception;

}
