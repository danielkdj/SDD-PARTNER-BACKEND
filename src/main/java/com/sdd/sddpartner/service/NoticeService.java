package com.sdd.sddpartner.service;

import java.util.List;

import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.domain.NoticeDto;

public interface NoticeService {

	void register(Notice notice, String empId) throws Exception;

	Notice read(Long noticeNo) throws Exception;

	void modify(Notice notice) throws Exception;

	void remove(Long noticeNo) throws Exception;

	List<Notice> threelist() throws Exception;
	List<Notice> list() throws Exception;
	List<Notice> searchlist(String title) throws Exception;

}
