package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.common.security.domain.CustomEmp;
import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService service;
	
	private final PasswordEncoder passwordEncoder;
	
	private final MessageSource messageSource;

	@PostMapping
	public ResponseEntity<Employee> register(@Validated @RequestBody Employee emp) throws Exception {
		log.info("emp.getUserName() = " + emp.getUserName());
		
		String inputPassword = emp.getUserPw();
		emp.setUserPw(passwordEncoder.encode(inputPassword));
		
		service.register(emp);

		log.info("register emp.getEmpId() = " + emp.getEmpId());
		
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Employee>> list() throws Exception {
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}

	@GetMapping("/{empId}")
	public ResponseEntity<Employee> read(@PathVariable("empId") String empId) throws Exception {
		Employee emp = service.read(empId);
		
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{empId}")
	public ResponseEntity<Void> remove(@PathVariable("empId") String empId) throws Exception {
		service.remove(empId);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	@PutMapping("/{empId}")
	public ResponseEntity<Employee> modify(@PathVariable("empId") String empId, @Validated @RequestBody Employee emp) throws Exception {
		log.info("modify : emp.getEmpId() = " + emp.getEmpId());
		log.info("modify : empId = " + empId);
		
		emp.setEmpId(empId);
		service.modify(emp);
		
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@PostMapping(value = "/setup", produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> setupAdmin(@Validated @RequestBody Employee emp) throws Exception {
		log.info("setupAdmin : emp.getUserName() = " + emp.getUserName());
		log.info("setupAdmin : service.countAll() = " + service.countAll());
		
		if(service.countAll() == 0) {
			String inputPassword = emp.getUserPw();
			emp.setUserPw(passwordEncoder.encode(inputPassword));
			
			emp.setJob("00");
			
			service.setupAdmin(emp);
	
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
		
		String message = messageSource.getMessage("common.cannotSetupAdmin", null, Locale.KOREAN);
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
		
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
	@GetMapping("/myinfo")
	public ResponseEntity<Employee> getMyInfo(@AuthenticationPrincipal CustomEmp customEmp) throws Exception {
		String empID = customEmp.getEmpId();
		log.info("register userNo = " + empID);

		Employee member = service.read(empID);
		
		member.setUserPw("");
		
		return new ResponseEntity<>(member, HttpStatus.OK);
	}
	
}
