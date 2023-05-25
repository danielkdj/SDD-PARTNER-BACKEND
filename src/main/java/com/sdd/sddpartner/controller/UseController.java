package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.service.UseService;
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
@RequestMapping("/use")
public class UseController {
	
	private final UseService service;

	@GetMapping("/{documentNo}")
	public ResponseEntity<Ea> read(@PathVariable("documentNo") Long documentNo) throws Exception {
		Ea ea = service.read(documentNo);
			
		return new ResponseEntity<>(ea, HttpStatus.OK);
	}

	@GetMapping("/main")
	public ResponseEntity<List<Ea>> fourList() throws Exception {
		log.info("list");
		return new ResponseEntity<>(service.fourlist(), HttpStatus.OK);
	}
//	@GetMapping("/useAll")
//	public ResponseEntity<List<Ea>> uselist() throws Exception {
//		log.info("list");
//		return new ResponseEntity<>(service.uselist(), HttpStatus.OK);
//	}
	@GetMapping("/list/{categoryId}")
	public ResponseEntity<List<Ea>> categoryList(@PathVariable("categoryId") Long categoryId) throws Exception {
		log.info("list");
		return new ResponseEntity<>(service.categorylist(categoryId), HttpStatus.OK);
	}
	@GetMapping("/schedule/{categoryId}")
	public ResponseEntity<List<Ea>> scheduleList(@PathVariable("categoryId") Long categoryId) throws Exception {
		log.info("list");
		return new ResponseEntity<>(service.schedulelist(categoryId), HttpStatus.OK);
	}

	@GetMapping("/search/{title}")
	public ResponseEntity<List<Ea>> searchList(@PathVariable("title") String title) throws Exception {
		log.info("list");
		return new ResponseEntity<>(service.searchlist(title), HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{documentNo}")
	public ResponseEntity<Ea> modify(@PathVariable("documentNo") Long documentNo, @Validated @RequestBody Ea ea) throws Exception {
		ea.setDocumentNo(documentNo);
		service.modify(ea);

		return new ResponseEntity<>(ea, HttpStatus.OK);
	}
	
}
