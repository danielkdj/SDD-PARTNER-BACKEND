package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Employee;

import java.util.List;

public interface EmployeeService {

	public void register(Employee emp) throws Exception;

	public Employee read(String empId) throws Exception;

	public void modify(Employee emp) throws Exception;

	public void remove(String empId) throws Exception;

	public List<Employee> list() throws Exception;
	
	public long countAll() throws Exception;
	
	public void setupAdmin(Employee emp) throws Exception;

	public String getCoin(String empId) throws Exception;
	
}
