package com.sdd.sddpartner.service;

import java.util.List;
import com.sdd.sddpartner.domain.Notice;

public interface NoticeService {

	public void create(Notice notice) throws Exception;

	public Notice select(Long noticeNo) throws Exception;

	public void update(Notice notice) throws Exception;

	public void delete(Long noticeNo) throws Exception;

	public List<Notice> searchList(String keyword) throws Exception;

}
