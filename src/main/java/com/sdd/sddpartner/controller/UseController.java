package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.domain.EaDto;
import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.domain.NoticeDto;
import com.sdd.sddpartner.service.UseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/use")
public class UseController {

	private final UseService service;

	@GetMapping("/{documentNo}")
	public ResponseEntity<EaDto> read(@PathVariable("documentNo") Long documentNo) throws Exception {
		EaDto eaDto = new EaDto(service.read(documentNo));
		return new ResponseEntity<>(eaDto, HttpStatus.OK);
	}

	@GetMapping("/main")
	public ResponseEntity<List<EaDto>> fourList() throws Exception {
		log.info("list");
		List<EaDto> eaDtoList = getEaDtoList(service.fourList());

		return new ResponseEntity<>(eaDtoList, HttpStatus.OK);
	}

	//전자결재 목록출력
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<EaDto>> categoryList(@PathVariable("categoryId") List<Long> categoryId) throws Exception {
		categoryId = setCategoryDefalt(categoryId);

		List<EaDto> eaDtoList = getEaDtoList(service.categoryList(categoryId));
		return new ResponseEntity<>(eaDtoList, HttpStatus.OK);
	}

	//전자결재 승인건(=사용일정) 목록출력
	@GetMapping("/schedule/{categoryId}")
	public ResponseEntity<List<EaDto>> scheduleList(@PathVariable("categoryId") List<Long> categoryId) throws Exception {
		categoryId = setCategoryDefalt(categoryId);

		List<EaDto> eaDtoList = getEaDtoList(service.scheduleList(categoryId));
		return new ResponseEntity<>(eaDtoList, HttpStatus.OK);
	}

	//항목, 상태 검색
	@GetMapping("/search/{categoryId}/{approve}")
	public ResponseEntity<List<EaDto>> searchList(@PathVariable("categoryId") List<Long> categoryId, @PathVariable("approve") List<Long> approve) throws Exception {
		categoryId = setCategoryDefalt(categoryId);
		approve = setApproveDefalt(approve);

		List<EaDto> eaDtoList = getEaDtoList(service.searchList(categoryId, approve));
		return new ResponseEntity<>(eaDtoList, HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{documentNo}")
	public ResponseEntity<EaDto> modify(@PathVariable("documentNo") Long documentNo, @Validated @RequestBody Ea ea) throws Exception {
		ea.setDocumentNo(documentNo);
		service.modify(ea);

		EaDto eaDto = new EaDto(ea);
		return new ResponseEntity<>(eaDto, HttpStatus.OK);
	}

	//entityList를 dtoList로 변환
	private List<EaDto> getEaDtoList(List<Ea> eaList){
		List<EaDto> eaDtoList = new ArrayList<>();

		for (Ea ea : eaList) {
			EaDto eaDto = new EaDto(ea);
			eaDtoList.add(eaDto);
		}
		return eaDtoList;
	}

	//categoryId 1이면 회의실, 2이면 차량검색으로 categoryId값 수정
	private  List<Long> setCategoryDefalt(List<Long> categoryId){
		if(categoryId.get(0)==1){
			categoryId.clear();
			categoryId.add(0, 12L);
			categoryId.add(1, 13L);
		}
		if(categoryId.get(0)==2){
			categoryId.clear();
			categoryId.add(0, 14L);
			categoryId.add(1, 15L);
		}
		return  categoryId;
	}

	//approve 9이면 1,2.3으로 값 수정
	private List<Long> setApproveDefalt(List<Long> approve) {
		if(approve.get(0)==9){
			approve.clear();
			approve.add(0, 1L);
			approve.add(1, 2L);
			approve.add(1, 3L);
		}
		return approve;
	}


}
