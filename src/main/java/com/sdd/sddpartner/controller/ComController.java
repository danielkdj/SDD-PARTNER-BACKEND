package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.ComDto;
import com.sdd.sddpartner.domain.Completion;
import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.domain.NoticeDto;
import com.sdd.sddpartner.service.ComService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/com")
public class ComController {
	
	private final ComService service;
	@GetMapping
	public ResponseEntity<List<ComDto>> list() throws Exception {
		log.info("list");
		List<ComDto> comDtoList = getComDtoList(service.list());
		return new ResponseEntity<>(comDtoList, HttpStatus.OK);
	}
	@GetMapping("{comNo}")
	public ResponseEntity<ComDto> read(@PathVariable("comNo") Long comNo) throws Exception {
		ComDto compDto = new ComDto(service.read(comNo));

		return new ResponseEntity<>(compDto, HttpStatus.OK);
	}

	@PatchMapping("/{comNo}")
	public ResponseEntity<Completion> modify(@PathVariable("comNo") Long comNo) throws Exception {
		service.modify(comNo);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	private List<ComDto> getComDtoList(List<Completion> completionList){
		List<ComDto> comDtoList = new ArrayList<>();

		for (Completion completion : completionList) {
			ComDto comDto = new ComDto(completion);
			comDtoList.add(comDto);
		}
		return comDtoList;
	}

	
}
