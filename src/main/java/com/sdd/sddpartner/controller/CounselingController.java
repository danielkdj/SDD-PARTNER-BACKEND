package com.sdd.sddpartner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sdd.sddpartner.domain.Counseling;
import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.dto.CounselingDto;
import com.sdd.sddpartner.service.CounselingService;
import com.sdd.sddpartner.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/counseling")
public class CounselingController {

	private final CounselingService service;
	private final EmployeeService empService;

	// HR 사용 메소드
	@GetMapping("/coun")
	public ResponseEntity<List<CounselingDto>> list() throws Exception {
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}

	@GetMapping("/coun/id/{counId}")
	public ResponseEntity<Counseling> getCounselingId(@PathVariable Long counId) throws Exception {
		Counseling coun = service.read(counId);
		return new ResponseEntity<>(coun, HttpStatus.OK);
	}

	@GetMapping("/coun/title/{counTitle}")
	public ResponseEntity<?> getCounByTitle(@PathVariable String counTitle) throws Exception {
		List<Counseling> coun = service.findByTitle(counTitle);
		return new ResponseEntity<>(coun, HttpStatus.OK);
	}

	@PostMapping(value = "/coun/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Counseling> createCounseling(@Validated @RequestBody Counseling coun) throws Exception {
		// empId를 사용하여 Employee를 찾습니다.
		Employee emp = empService.findById(coun.getEmployee().getEmpId());

		if (emp == null) {
			// empId에 해당하는 Employee가 없는 경우, 적절한 예외를 던집니다.
			throw new IllegalArgumentException("사원번호가 다릅니다 : " + coun.getEmployee().getEmpId());
		}

		// 사원 정보를 상담 정보에 추가합니다.
		coun.setEmployee(emp);

		// 상담 정보를 저장합니다.
		Counseling counseling = service.save(coun);

		return new ResponseEntity<>(counseling, HttpStatus.CREATED);
	}
	@PutMapping(value = "/coun/update/{counId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Counseling> updateCounseling(@PathVariable Long counId, @RequestBody Map<String, Object> requestBody) throws Exception {
		Counseling counseling = new Counseling();
		counseling.setCounAnswer((String) requestBody.get("counAnswer"));
		counseling.setCounAt(LocalDate.now());

		Counseling updatedCounseling = service.update(counId, counseling);
		return new ResponseEntity<>(updatedCounseling, HttpStatus.OK);
	}

	@DeleteMapping("/coun/delete/{counId}")
	public ResponseEntity<Void> deleteCounseling(@PathVariable Long counId) throws Exception{
		service.delete(counId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
