package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Completion;
import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.repository.ComRepository;
import com.sdd.sddpartner.repository.EduInfoRepository;
import com.sdd.sddpartner.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class ComServiceImpl implements ComService {

	private final ComRepository repository;
	private final EmployeeRepository employeeRepository;
	private final EduInfoRepository eduInfoRepository;

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

	@Override
	public List<Completion> register(Long eduId, Long years, Long quarters) {
		List<Completion> addList = new ArrayList<>();

		Completion newCompletion = new Completion();
			newCompletion.setCompletion('N');
			newCompletion.setYears(years);
			newCompletion.setQuarters(quarters);
			newCompletion.setEduInfo(eduInfoRepository.getOne(eduId));

		List<Employee> allEmployeeList =employeeRepository.findAll();

		for(Employee e : allEmployeeList) {
			newCompletion.setEmployee(e);
			repository.save(newCompletion);

			addList.add(newCompletion);
		}

		return addList;
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
