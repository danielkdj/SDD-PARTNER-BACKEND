package com.sdd.sddpartner.service;

import java.util.List;

import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.repository.EmployeeRepository;
import com.sdd.sddpartner.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository repository;
	private final EmployeeRepository empRepository;

	@Override
	public void register(Notice notice, String empId) throws Exception {
		Employee emp = empRepository.getOne(empId);
		log.info("유저정보 출력"+ emp);
		notice.setEmployee(emp);
		repository.save(notice);
	}

	@Override
	public Notice read(Long noticeNo) throws Exception {
		return repository.getOne(noticeNo);
	}

	@Override
	public void modify(Notice newNotice) throws Exception {
		repository.findById(newNotice.getNoticeNo())
				.map(notice -> {
					notice.setTitle(newNotice.getTitle());
					notice.setContent(newNotice.getContent());
					return repository.save(notice);
				})
				.orElseGet(() -> repository.save(newNotice));
	}

	@Override
	public void remove(Long noticeNo) throws Exception {
		repository.deleteById(noticeNo);
	}

	@Override
	public List<Notice> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "noticeNo"));
	}
	@Override
	public List<Notice> threelist() throws Exception {
		return repository.findTop3By(Sort.by(Direction.DESC, "noticeNo"));
	}
	@Override
	public List<Notice> searchlist(String title) throws Exception {
		return repository.findByTitleContaining(Sort.by(Direction.DESC, "noticeNo"),title);
	}

}
