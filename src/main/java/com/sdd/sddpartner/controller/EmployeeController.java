package com.sdd.sddpartner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.prop.ShopProperties;
import com.sdd.sddpartner.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	//attendance 사용 메소드

	//findAll count
	@GetMapping("/ep/count")
	public ResponseEntity<Integer> countAllEmployees() throws Exception {
		List<Employee> empList = service.findAll();
		int count = empList.size();

		return new ResponseEntity<>(count, HttpStatus.OK);
	}
	@GetMapping("/ep/onleave/count")
	public ResponseEntity<Integer> countEmployeesOnLeave() throws Exception {
		int count = service.countByEmpStatus(3);  // 휴가 상태

		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@GetMapping("/ep/overworking/count")
	public ResponseEntity<Integer> countEmployeesOverWorking() throws Exception {
		int count = service.countByEmpStatus(4);  // 연장근무 상태

		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@GetMapping("/ep/working/count")
	public ResponseEntity<Integer> countEmployeesWorking() throws Exception {
		int count = service.countByEmpStatus(1);  // 출근 상태

		return new ResponseEntity<>(count, HttpStatus.OK);
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
	public ResponseEntity<Map<String, Object>> createEmployee(@RequestPart("emp") String empJson, @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		Map<String, Object> response = new HashMap<>();

		try {
			Employee emp = objectMapper.readValue(empJson, Employee.class);

			if (emp.getHireDate() == null) {
				emp.setHireDate(LocalDate.now());
			}

			String rawPassword = emp.getPassword();

			if (file != null && !file.isEmpty()) {
				String imageUrl = saveImageToFile(file, emp.getEmpId());
				emp.setEmpImg(imageUrl);
			}

			Employee createdEmployee = service.save(emp);

			if(createdEmployee != null) {
				String formattedPhoneNumber = convertToE164Format(createdEmployee.getPhone());
				String msg = "SSD-Partner 입니다. 당신의 사번: " + createdEmployee.getEmpId() + "이며, 초기비밀번호: " + rawPassword + " 입니다. 비밀번호는 꼭 변경해서 사용하세요!";
				sendSms(formattedPhoneNumber, msg);
				response.put("success", true);
				response.put("employee", createdEmployee);
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			} else {
				response.put("success", false);
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (JsonProcessingException e) {
			response.put("success", false);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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

	@PostMapping("/ep/login")
	public ResponseEntity<String> login(@RequestBody Employee loginRequest) throws Exception {
		Employee existingEmployee = service.findById(loginRequest.getEmpId());

		// 요청된 사번에 해당하는 사원이 없다면 오류 메시지를 반환
		if (existingEmployee == null) {
			return new ResponseEntity<>("사원을 찾을 수 없습니다", HttpStatus.NOT_FOUND);
		}

		boolean passwordMatch;
		// 기존 비밀번호가 이미 암호화되어 있는지 확인
		if (passwordEncoder.matches(existingEmployee.getPassword(), loginRequest.getPassword())) {
			// 비밀번호가 이미 암호화되어 있다면, 요청으로부터 받은 비밀번호의 암호화된 버전과 비교
			passwordMatch = passwordEncoder.matches(loginRequest.getPassword(), existingEmployee.getPassword());
		} else {
			// 비밀번호가 암호화되어 있지 않다면, 직접 비교
			passwordMatch = existingEmployee.getPassword().equals(loginRequest.getPassword());

			// 비밀번호가 암호화되어 있지 않고 일치한다면, 비밀번호를 암호화하여 데이터베이스에 저장
			if (passwordMatch) {
				String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());
				existingEmployee.setPassword(encodedPassword);
				service.save(existingEmployee);
			}
		}
		// 비밀번호가 일치하지 않는다면 오류 메시지를 반환
		if (!passwordMatch) {
			return new ResponseEntity<>("잘못된 로그인 정보입니다", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
	}
}
