package com.sdd.sddpartner.controller;

import java.util.ArrayList;
import java.util.List;

import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.dto.NoticeDto;
import com.sdd.sddpartner.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<NoticeDto> read(@PathVariable("noticeNo") Long noticeNo) throws Exception {
		NoticeDto notice = new NoticeDto(service.read(noticeNo));
		return new ResponseEntity<>(notice, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<NoticeDto>> list() throws Exception {
		log.info("list");
		List<NoticeDto> noticeDtoList = getNoticeDtoList(service.list());
		return new ResponseEntity<>(noticeDtoList, HttpStatus.OK);
	}
	@GetMapping("/main")
	public ResponseEntity<List<NoticeDto>> threeList() throws Exception {
		log.info("list");
		List<NoticeDto> noticeDtoList = getNoticeDtoList(service.threelist());
		return new ResponseEntity<>(noticeDtoList, HttpStatus.OK);
	}

	@GetMapping("/search/{title}")
	public ResponseEntity<List<NoticeDto>> searchList(@PathVariable("title") String title) throws Exception {
		log.info("list");
		List<NoticeDto> noticeDtoList = getNoticeDtoList(service.searchlist(title));
		return new ResponseEntity<>(noticeDtoList, HttpStatus.OK);
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
			String empId="EMP-123456";
		service.register(notice, empId);
		
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

	private List<NoticeDto> getNoticeDtoList(List<Notice> noticeList){
		List<NoticeDto> noticeDtoList = new ArrayList<>();

		for (Notice notice : noticeList) {
			NoticeDto noticeDto = new NoticeDto(notice);
			noticeDtoList.add(noticeDto);
		}
		return noticeDtoList;
	}
}
