package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Department;
import com.sdd.sddpartner.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository repository;

	@Override
	public List<Department> list() throws Exception {
		return repository.findAll(Sort.by(Direction.ASC, "deptNo") );
	}

}
