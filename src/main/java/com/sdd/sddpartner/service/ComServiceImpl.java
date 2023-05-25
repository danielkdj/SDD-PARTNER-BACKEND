package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Completion;
import com.sdd.sddpartner.repository.ComRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class ComServiceImpl implements ComService {

	private final ComRepository repository;

//	@Override
//	public Completion read(Long ComNo) throws Exception {
//		return repository.getOne(ComNo);
//	}
	@Override
	public List<Completion> list() throws Exception {
		return repository.findAll(Sort.by(Direction.ASC, "comNo"));
	}
	public void modify(Long comNo) throws Exception {
		repository.findById(comNo)
				.map(completion -> {
					if(completion.getCompletion()=='N'){
						completion.setCompletion('Y');

					}else{
						completion.setCompletion('N');

					}
					return repository.save(completion);
				})
				.orElseThrow();
	}

//	@Override
//	public List<Completion> list(Completion search) throws Exception {
//		return repository.findByEduInfo_EduIdContainingAndYearsContainingAndQuartersContainingAndCompletionContaining(Sort.by(Direction.ASC, "comNo"), search.getEduInfo().getEduId(), search.getYears(), search.getQuarters(), search.getCompletion());
//	}
//	@Override
//	public List<Completion> list(Completion search) throws Exception {
//		return repository.findByCompletionContaining(Sort.by(Direction.ASC, "comNo"), search.getCompletion());
//	}

}
