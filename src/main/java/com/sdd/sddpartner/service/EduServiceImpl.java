package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Edu;
import com.sdd.sddpartner.domain.SearchCategoryDate;
import com.sdd.sddpartner.repository.EduRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EduServiceImpl implements EduService {

	private final EduRepository repository;

	@Override
	public void create(Edu edu) throws Exception {
		repository.save(edu);
	}

	@Override
	public Edu select(Long eduNo) throws Exception {
		Optional<Edu> opt = repository.findById(eduNo);
		return opt.orElseThrow(() -> new Exception("select result set null"));
	}

	@Override
	public void update(Edu edu) throws Exception {
		Optional<Edu> opt = repository.findById(edu.getEduNo());
		Edu entity = opt.orElseThrow(() -> new Exception("update No null"));
		entity.setTitle(edu.getTitle());
		entity.setContent(edu.getContent());
		entity.setInstitution(edu.getInstitution());
		entity.setPresenter(edu.getPresenter());
		entity.setPlace(edu.getPlace());
		entity.setCount(edu.getCount());
		entity.setEduStart(edu.getEduStart());
		entity.setEduEnd(edu.getEduEnd());
		//null 체크 추가해야함

		repository.save(entity);
	}

	@Override
	public void delete(Long eduNo) throws Exception {
		repository.deleteById(eduNo);
	}

	@Override
	public List<Edu> List() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "eduNo"));
	}

	@Override
	public List<Edu> searchList(SearchCategoryDate search) throws Exception {
		return repository.findByEduIdAndEduStartGreaterThanEqualAndEduEndLessThanEqual(Sort.by(Direction.DESC, "eduNo"), search.getCategory(), search.getStartDateTime(), search.getEndDateTime());
	}

}
