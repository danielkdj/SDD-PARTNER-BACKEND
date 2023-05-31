package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Drv;
import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.dto.EaDto;
import com.sdd.sddpartner.service.DrvService;
import com.sdd.sddpartner.service.UseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/use")
public class UseController {

	private final UseService service;
	private final DrvService dvrService;

	@GetMapping("/{documentNo}")
	public ResponseEntity<EaDto> read(@PathVariable("documentNo") Long documentNo) throws Exception {
		EaDto eaDto = new EaDto(service.read(documentNo));
		return new ResponseEntity<>(eaDto, HttpStatus.OK);
	}

	@GetMapping("/main")
	public ResponseEntity<List<EaDto>> fourList() throws Exception {
		List<EaDto> eaDtoList = getEaDtoList(service.fourList());
		return new ResponseEntity<>(eaDtoList, HttpStatus.OK);
	}

	@GetMapping("/mainCount")
	public ResponseEntity<List<Long>> mainCount() throws Exception {
		log.info("list");
		//approv 1 , categoryId 12,13 | 14,15
//		Long holdApprove = 1L;
		List<Long> countList = new ArrayList<>(2);
		countList.add(service.mainCount(getRoomCategoryId()));
		countList.add(service.mainCount(getCarCategoryId()));
		return new ResponseEntity<>(countList, HttpStatus.OK);
	}

	@GetMapping("/mainDate")
	public ResponseEntity<List<LocalDate>> mainDate() throws Exception {
		List<LocalDate> dateList = new ArrayList<>(2);

		Ea oldestRoomNotApprove = service.mainDate(getRoomCategoryId());
		Ea oldestCarNotApprove = service.mainDate(getCarCategoryId());

		if(oldestRoomNotApprove!=null){
			dateList.add(0,oldestRoomNotApprove.getCreatedAt());
		}else{ dateList.add(0,null);
		}

		if(oldestCarNotApprove!=null){
			dateList.add(1,oldestCarNotApprove.getCreatedAt());
		}else{ dateList.add(0,null);
		}

		return new ResponseEntity<>(dateList, HttpStatus.OK);
	}

	//전자결재 목록출력
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<EaDto>> categoryList(@PathVariable("categoryId") List<Long> categoryId) throws Exception {
		List<Long> categoryIdList = getCategoryDefaultList(categoryId);

		List<EaDto> eaDtoList = getEaDtoList(service.categoryList(categoryIdList));
		return new ResponseEntity<>(eaDtoList, HttpStatus.OK);
	}

	//전자결재 승인건(=사용일정) 목록출력
	@GetMapping("/schedule/{categoryId}")
	public ResponseEntity<List<EaDto>> scheduleList(@PathVariable("categoryId") List<Long> categoryId) throws Exception {
		List<Long> categoryIdList = getCategoryDefaultList(categoryId);

		List<EaDto> eaDtoList = getEaDtoList(service.scheduleList(categoryIdList));
		return new ResponseEntity<>(eaDtoList, HttpStatus.OK);
	}

	//항목, 상태 검색
	@GetMapping("/search/{categoryId}/{approve}")
	public ResponseEntity<List<EaDto>> searchList(@PathVariable("categoryId") List<Long> categoryId,
												  @PathVariable("approve") List<Long> approve) throws Exception {
		List<Long> categoryIdList = getCategoryDefaultList(categoryId);
		approve = setApproveDefalt(approve);

		List<EaDto> eaDtoList = getEaDtoList(service.searchList(categoryIdList, approve));
		return new ResponseEntity<>(eaDtoList, HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{documentNo}/{approve}")
	public ResponseEntity<Void> modify(
			@PathVariable("documentNo") Long documentNo,
			@PathVariable("approve") Long approve) throws Exception {

		service.modify(documentNo,approve);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PatchMapping("/car/{documentNo}/{approve}")
	public ResponseEntity<Long> modifyAndCreate(
			@PathVariable("documentNo") Long documentNo,
			@PathVariable("approve") Long approve) throws Exception {

		service.modifyAndCreate(documentNo,approve);

		Long drvNo = 0L;
		if(approve==2){//차량승인일 때
			Drv newDrv = dvrService.register(documentNo);
			drvNo = newDrv.getDrvNo();
		}
		return new ResponseEntity<>(drvNo, HttpStatus.OK);
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


	private List<Long> getRoomCategoryId(){
		List<Long> roomCategoryId = Arrays.asList(12L,13L);
		return roomCategoryId;
	}

	private List<Long> getCarCategoryId(){
		List<Long> carCategoryId = Arrays.asList(14L,15L);
		return carCategoryId;
	}

//categoryId 1이면 회의실, 2이면 차량검색으로 categoryId값 수정
private  List<Long> getCategoryDefaultList(List<Long> categoryId){
	if(categoryId.get(0)==1){
		categoryId = getRoomCategoryId();
	}else if(categoryId.get(0)==2){
		categoryId = getCarCategoryId();
	}
	return  categoryId;
}

	//approve 9이면 1,2.3으로 값 수정
	private List<Long> setApproveDefalt(List<Long> approve) {
		if(approve.get(0)==9){
			approve.clear();
			approve.add(1L);
			approve.add(2L);
			approve.add(3L);
		}
		return approve;
	}

}
