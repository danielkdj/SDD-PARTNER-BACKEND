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

	@Override
	public List<Completion> list() throws Exception {
		return repository.findAll(Sort.by(Direction.ASC, "comNo"));
	}

	@Override
	public List<Completion> searchList(List<Long> eduIds, List<Character> completions,List<Long> deptNos, Long years, List<Long> quarters) {
		return repository.findByEduInfo_EduIdInAndCompletionInAndEmployee_Dept_DeptNoInAndYearsAndQuartersIn(Sort.by(Direction.ASC, "comNo"), eduIds, completions, deptNos, years, quarters);
	}
	@Override
	public Long count(List<Long> eduIds, List<Character> completions, Long years, List<Long> quarters) {
		return repository.countByEduInfo_EduIdInAndCompletionInAndYearsAndQuartersIn(Sort.by(Direction.ASC, "comNo"), eduIds, completions, years, quarters);
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

}
