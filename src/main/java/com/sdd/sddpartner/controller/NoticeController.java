package com.sdd.sddpartner.controller;

import java.util.List;

import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.dto.NoticeDto;
import com.sdd.sddpartner.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	private final NoticeService service;

	@GetMapping("/{noticeNo}")
	public ResponseEntity<Notice> read(@PathVariable("noticeNo") Long noticeNo) throws Exception {
		Notice notice = service.read(noticeNo);
			
		return new ResponseEntity<>(notice, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Notice>> list() throws Exception {
		log.info("list");
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Notice> register(@Validated @RequestBody Notice notice) throws Exception {
		log.info("register");
		//controller에서는 매개변수로 Authentication를 받아올 수 있고
		//Principle을 사용할 수도 있다.
		//service나 다른 계층에서는 전역변수 SecurityContextHolder를 통해서 Authentication를 구할 수도 있다.
		//별도로 user정보를 내어주는 controller를 생성하고 종속성으로 사용하는 방법도 있다.
		//Authentication a = SecurityContextHolder.getContext().getAuthentication();
		//String username = a.getName();
		//String username = authentication.getName();
//		if(username.length()==0){
//		}
			String username="류준열";
		service.register(notice, username);
		
		log.info("register notice.getNoticeNo() = " + notice.getNoticeNo());
		
		return new ResponseEntity<>(notice, HttpStatus.OK);
	}

	@DeleteMapping("/{noticeNo}")
	public ResponseEntity<Void> remove(@PathVariable("noticeNo") Long noticeNo) throws Exception {
		service.remove(noticeNo);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{noticeNo}")
	public ResponseEntity<Notice> modify(@PathVariable("noticeNo") Long noticeNo, @Validated @RequestBody Notice notice) throws Exception {
		notice.setNoticeNo(noticeNo);
		service.modify(notice);

		return new ResponseEntity<>(notice, HttpStatus.OK);
	}
	
}
