package com.sdd.sddpartner.service;

import java.util.List;

import com.sdd.sddpartner.domain.Notice;

public interface NoticeService {

	public void register(Notice notice, String userName) throws Exception;

	public Notice read(Long noticeNo) throws Exception;

	public void modify(Notice notice) throws Exception;

	public void remove(Long noticeNo) throws Exception;

	public List<Notice> list() throws Exception;
	
}
