package com.sdd.sddpartner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sdd.sddpartner.domain.Counseling;
import com.sdd.sddpartner.dto.CounselingDto;
import com.sdd.sddpartner.service.CounselingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/counseling")
public class CounselingController {

	private final CounselingService service;

	// HR 사용 메소드
	@GetMapping("/coun")
	public ResponseEntity<List<CounselingDto>> list() throws Exception {
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
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
		Counseling counseling = service.save(coun);
		return new ResponseEntity<>(counseling, HttpStatus.CREATED);
	}

	@PutMapping("/coun/update/{counId}")
	public ResponseEntity<Counseling> updateCounseling(@PathVariable Long counId, @RequestPart("coun") String counJson, @RequestPart(value = "file", required = false) MultipartFile file) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		Counseling coun = objectMapper.readValue(counJson, Counseling.class);

		Counseling updatedCounseling = service.update(counId, coun);
		return new ResponseEntity<>(updatedCounseling, HttpStatus.OK);
	}

	@DeleteMapping("/coun/delete/{counId}")
	public ResponseEntity<Void> deleteCounseling(@PathVariable Long counId) throws Exception{
		service.delete(counId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
