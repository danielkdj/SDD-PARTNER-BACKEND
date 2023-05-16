package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Edu;
import com.sdd.sddpartner.domain.SearchCategoryDate;
import com.sdd.sddpartner.repository.EduRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
		return repository.getOne(eduNo);
	}

	@Override
	public void update(Edu edu) throws Exception {
		Edu eduEntity = repository.getOne(edu.getEduNo());

		eduEntity.setTitle(edu.getTitle());
		eduEntity.setContent(edu.getContent());
		
		repository.save(eduEntity);
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
