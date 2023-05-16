package com.sdd.sddpartner.controller;

import java.util.List;

import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notices")
public class NoticeController {
	
	private final NoticeService service;

	@GetMapping("/{noticeNo}")
	public ResponseEntity<Notice> select(@PathVariable("noticeNo") Long noticeNo) throws Exception {
		Notice notice = service.select(noticeNo);

		return new ResponseEntity<>(notice, HttpStatus.OK);
	}

	@GetMapping("/{keyword}")
	public ResponseEntity<List<Notice>> searchList(@PathVariable("keyword") String keyword) throws Exception {
		log.info("searchList");

			return new ResponseEntity<>(service.searchList(keyword), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Notice> create(@Validated @RequestBody Notice notice) throws Exception {
		log.info("create");
		
		service.create(notice);
		
		log.info("create notice.getNoticeNo() = " + notice.getNoticeNo());
		
		return new ResponseEntity<>(notice, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{noticeNo}")
	public ResponseEntity<Void> delete(@PathVariable("noticeNo") Long noticeNo) throws Exception {
		service.delete(noticeNo);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{noticeNo}")
	public ResponseEntity<Notice> update(@PathVariable("noticeNo") Long noticeNo, @Validated @RequestBody Notice notice) throws Exception {
		notice.setNoticeNo(noticeNo);
		service.update(notice);
		
		return new ResponseEntity<>(notice, HttpStatus.OK);
	}
	
}
