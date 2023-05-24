package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Department;
import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.service.DepartmentService;
import com.sdd.sddpartner.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/dept")
public class DepartmentController {
	
	private final DepartmentService service;
	@GetMapping()
	public ResponseEntity<List<Department>> list() throws Exception {
		log.info("list");
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}
	
}
