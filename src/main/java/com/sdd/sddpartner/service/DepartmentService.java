package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Completion;
import com.sdd.sddpartner.domain.Department;

import java.util.List;

public interface DepartmentService {
	List<Department> list() throws Exception;
}
