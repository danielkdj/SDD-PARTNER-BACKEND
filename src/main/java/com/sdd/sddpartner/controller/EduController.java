package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Edu;
import com.sdd.sddpartner.domain.SearchCategoryDate;
import com.sdd.sddpartner.service.EduService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/edus")
public class EduController {
	
	private final EduService service;

	@GetMapping("/{eduNo}")
	public ResponseEntity<Edu> select(@PathVariable("eduNo") Long eduNo) throws Exception {
		Edu edu = service.select(eduNo);

		return new ResponseEntity<>(edu, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Edu>> searchList(@PathVariable("title") SearchCategoryDate search) throws Exception {
		log.info("searchList");

			return new ResponseEntity<>(service.searchList(search), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Edu> create(@Validated @RequestBody Edu edu) throws Exception {
		log.info("create");
		
		service.create(edu);
		
		log.info("create edu.getEduNo() = " + edu.getEduNo());
		
		return new ResponseEntity<>(edu, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{eduNo}")
	public ResponseEntity<Void> delete(@PathVariable("eduNo") Long eduNo) throws Exception {
		service.delete(eduNo);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{eduNo}")
	public ResponseEntity<Edu> update(@PathVariable("eduNo") Long eduNo, @Validated @RequestBody Edu edu) throws Exception {
		edu.setEduNo(eduNo);
		service.update(edu);
		
		return new ResponseEntity<>(edu, HttpStatus.OK);
	}
	
}
