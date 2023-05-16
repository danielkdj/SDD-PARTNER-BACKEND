package com.sdd.sddpartner.service;

import java.util.List;
import java.util.Optional;

import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.repository.NoticeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository repository;

	@Override
	public void create(Notice notice) throws Exception {
		repository.save(notice);
	}

	@Override
	public Notice select(Long noticeNo) throws Exception {
		Optional<Notice> opt = repository.findById(noticeNo);
		return opt.orElseThrow(() -> new Exception("select result set null"));
	}

	@Override
	public void update(Notice notice) throws Exception {
		Optional<Notice> opt = repository.findById(notice.getNoticeNo());
		Notice entity = opt.orElseThrow(() -> new Exception("update No null"));
		entity.setTitle(notice.getTitle());
		entity.setContent(notice.getContent());
		
		repository.save(entity);
	}

	@Override
	public void delete(Long noticeNo) throws Exception {
		repository.deleteById(noticeNo);
	}

	@Override
	public List<Notice> searchList(String keyword) throws Exception {
		if(keyword.length()==0){
		return repository.findAll(Sort.by(Direction.DESC, "noticeNo"));
		}else{
		return repository.findByTitleContains(Sort.by(Direction.DESC, "noticeNo"), keyword);
		}
	}
}
