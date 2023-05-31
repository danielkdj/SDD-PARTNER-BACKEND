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
		service.register(notice, notice.getEmployee().getEmpId());
		
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
