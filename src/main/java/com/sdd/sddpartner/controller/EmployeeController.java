package com.sdd.sddpartner.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sdd.sddpartner.common.security.domain.CustomEmp;
import com.sdd.sddpartner.common.util.UploadFileUtils;
import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.prop.ShopProperties;
import com.sdd.sddpartner.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService service;
	private final PasswordEncoder passwordEncoder;
	private final MessageSource messageSource;
	private final ShopProperties shopProperties;
	private final String ACCOUNT_SID = "AC3773a6b49ca7ffaf5c1fb3104188844a";
	private final String AUTH_TOKEN = "243158d500afd4c91f3b0789705ee282";
	private final String FROM_NUMBER = "+13158175187";
	@PostConstruct
	public void init() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}

	@PostMapping("/register")
	public ResponseEntity<Employee> register(@Validated @RequestBody Employee emp) throws Exception {
		log.info("emp.getName() = " + emp.getName());
		
		String inputPassword = emp.getPassword();
		emp.setPassword(passwordEncoder.encode(inputPassword));
		
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
		log.info("setupAdmin : emp.getUserName() = " + emp.getName());
		log.info("setupAdmin : service.countAll() = " + service.countAll());
		
		if(service.countAll() == 0) {
			String inputPassword = emp.getPassword();
			emp.setPassword(passwordEncoder.encode(inputPassword));
			
			emp.setEmpRank("00");
			
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
		Employee emp = service.read(empID);
		emp.setPassword("");
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	// HR 사용 메소드
	@GetMapping("/ep")
	public ResponseEntity<List<Employee>> getAllEmployees() throws Exception {
		List<Employee> empList = service.findAll();

		for (Employee emp : empList) {
			String imagePath = emp.getEmpImg();
			String imageUrl = getWebPath(imagePath);
			emp.setEmpImg(imageUrl);
		}
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	private String getWebPath(String imagePath) {
		String fileSystemBasePath = "C:/SDD/public/img/";
		String serverBasePath = "http://localhost:3030/img/";

		if (imagePath != null && imagePath.startsWith(fileSystemBasePath)) {
			return serverBasePath + imagePath.substring(fileSystemBasePath.length());
		} else {
			return imagePath;
		}
	}

	private String saveImageToFile(MultipartFile file, String empId) throws Exception {
		if (file == null || file.isEmpty()) {
			return null;
		}
		String fileName = empId + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
		String filePath = "C:/SDD/public/img/" + fileName;
		File dest = new File(filePath);
		file.transferTo(dest);
		return filePath;
	}

	@PostMapping(value = "/ep/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestPart("emp") String empJson, @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		Employee emp = objectMapper.readValue(empJson, Employee.class);

		if (file != null && !file.isEmpty()) {
			String imageUrl = saveImageToFile(file, emp.getEmpId());
			emp.setEmpImg(imageUrl);
		}
		Employee createdEmployee = service.save(emp);

		String formattedPhoneNumber = convertToE164Format(createdEmployee.getPhone());
		String msg = "SSD-Partner 입니다. 당신의 사번: " + createdEmployee.getEmpId() + "이며, 초기비밀번호: " + createdEmployee.getEmpId() + " 입니다. 비밀번호는 꼭 변경해서 사용하세요!";
		sendSms(formattedPhoneNumber, msg);

		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}

	private void sendSms(String phoneNumber, String msg) {
		Message message = Message.creator(
						new PhoneNumber(phoneNumber),
						new PhoneNumber(FROM_NUMBER),
						msg)
				.create();

		System.out.println("Sent message w/ SID: " + message.getSid());
	}

	private String convertToE164Format(String phoneNumber) {
		String formattedPhoneNumber = phoneNumber.replaceAll("-", "");
		formattedPhoneNumber = "+82" + formattedPhoneNumber.substring(1);
		return formattedPhoneNumber;
	}

	@PutMapping("/ep/update/{empId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String empId, @RequestPart("emp") String empJson, @RequestPart(value = "file", required = false) MultipartFile file) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		Employee emp = objectMapper.readValue(empJson, Employee.class);

		if (file != null && !file.isEmpty()) {
			String imageUrl = saveImageToFile(file, emp.getEmpId());
			emp.setEmpImg(imageUrl);
		}

		Employee updatedEmployee = service.update(empId, emp);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}

	@GetMapping("/ep/name/{name}")
	public List<Employee> getEmployeeByName(@PathVariable String name) throws Exception{
		return service.findByName(name);
	}

	@DeleteMapping("/ep/delete/{empId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String empId) throws Exception{
		service.delete(empId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
