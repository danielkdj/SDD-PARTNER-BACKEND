package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.domain.EmployeeAuth;
import com.sdd.sddpartner.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository repository;

	@Override
	public void register(Employee emp) throws Exception {
		Employee empEntity = new Employee();

		EmployeeAuth empAuth = new EmployeeAuth();
		empAuth.setAuth("ROLE_EMPLOYEE");
		
		empEntity.addAuth(empAuth);
		
		repository.save(empEntity);
		
		emp.setEmpId(empEntity.getEmpId());
	}

	@Override
	public Employee read(String empId) throws Exception {
		return repository.getOne(empId);
	}

	@Override
	public void modify(Employee emp) throws Exception {
		Employee empEntity = repository.getOne(emp.getEmpId());
		empEntity.setName(emp.getName());
		empEntity.setEmpPosition(emp.getEmpPosition());
		
		List<EmployeeAuth> empAuthList = empEntity.getAuthList();
		List<EmployeeAuth> authList = emp.getAuthList();
		for(int i = 0; i < authList.size(); i++) {
			EmployeeAuth auth = authList.get(i);
			
			if(i < empAuthList.size()) {
				EmployeeAuth empAuth = empAuthList.get(i);
				empAuth.setAuth(auth.getAuth());
			}
		}
		
		repository.save(empEntity);
	}

	@Override
	public void remove(String empId) throws Exception {
		repository.deleteById(empId);
	}

	@Override
	public List<Employee> list() throws Exception {
		List<Object[]> valueArrays = repository.listAllEmployee();
		
		List<Employee> empList = new ArrayList<Employee>();
		for(Object[] valueArray : valueArrays) {
			Employee emp = new Employee();
			// employee Entity 활용
			emp.setEmpId((String) valueArray[0]);
			emp.setName((String) valueArray[1]);
			emp.setPassword((String) valueArray[2]);
			emp.setEmpImg((String) valueArray[3]);
			emp.setEmpSSN((String) valueArray[4]);
			emp.setGender((String) valueArray[5]);
			emp.setMarriage((String) valueArray[6]);
			emp.setEmail((String) valueArray[7]);
			emp.setEmpSpot((String) valueArray[8]);
			emp.setEmpPosition((String) valueArray[9]);
			emp.setEmpRank((String) valueArray[10]);
			emp.setEmpStatus((Integer) valueArray[11]);
			emp.setClassification((Integer) valueArray[12]);
			emp.setEmpClassification((Integer) valueArray[13]);
			emp.setAdmission((Integer) valueArray[14]);
			emp.setHireDate((LocalDateTime) valueArray[15]);
			emp.setLeaveDate((LocalDateTime) valueArray[16]);
			emp.setLeaveReason((String) valueArray[17]);
			emp.setLeaveIs((String) valueArray[18]);
			emp.setLeaveCode((Integer) valueArray[19]);
			emp.setAwards((String) valueArray[21]);
			emp.setQualifications((String) valueArray[22]);
			emp.setPermission((String) valueArray[23]);
			emp.setDeptNo((Integer) valueArray[24]);

			empList.add(emp);
		}
		
		return empList;
	}
	
	@Override
	public long countAll() throws Exception {
		return repository.count();
	}
	
	@Override
	public void setupAdmin(Employee emp) throws Exception {
		Employee empEntity = new Employee();
	
		EmployeeAuth empAuth = new EmployeeAuth();
		empAuth.setAuth("ROLE_ADMIN");
		empEntity.addAuth(empAuth);
		
		repository.save(empEntity);
	}
	
	@Override
	public String getCoin(String empId) throws Exception {
		Employee emp = repository.getOne(empId);
		
		return emp.getCoin();
	}
	
}
