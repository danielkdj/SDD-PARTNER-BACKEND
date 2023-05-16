package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	public List<Employee> findByUserId(String userId);
	
	@Query("SELECT e.empId, e.userId, e.userPw, e.userName, e.regDate "
			+ "FROM Employee e "
			+ "ORDER BY e.regDate DESC")
	public List<Object[]> listAllEmployee();
	
}
