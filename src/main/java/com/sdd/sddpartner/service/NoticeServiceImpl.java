package com.sdd.sddpartner.service;

import java.util.List;
import java.util.Optional;

import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.domain.User;
import com.sdd.sddpartner.repository.EmployeeRepository;
import com.sdd.sddpartner.repository.NoticeRepository;
import com.sdd.sddpartner.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository repository;
	private final UserRepository userRepository;

	@Override
	public void register(Notice notice, String userName) throws Exception {
		User user = userRepository.findByUserName(userName);
		log.info("유저정보 출력"+ user.toString());
		notice.setUsers(user);
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
	
}
