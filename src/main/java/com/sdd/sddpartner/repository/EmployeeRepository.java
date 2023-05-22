package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	List<Employee> findByEmpId(String empId);

	@Query("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Employee> findByName(@Param("name") String name);

	@Query("SELECT e.empId, e.name, e.password, e.empImg, e.empSsn," +
			" e.gender, e.marriage, e.phone, e.email, e.salary," +
			" e.accountNo, e.address, e.empSpot, e.empPosition, e.empRank," +
			" e.empStatus, e.classification, e.empClassification, e.admission, e.hireDate," +
			" e.leaveDate, e.leaveReason, e.leaveIs, e.leaveCode, e.awards," +
			" e.qualifications, e.permission, e.deptNo, e.annual, e.offYear," +
			" e.offChildcare, e.offMarriage, e.offPregnancy, e.offChildbirth, e.offReward "
			+ "FROM Employee e "
			+ "ORDER BY e.hireDate DESC")
	List<Object[]> listAllEmployee();

}
