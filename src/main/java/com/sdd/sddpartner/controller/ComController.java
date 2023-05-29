package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.dto.ComDto;
import com.sdd.sddpartner.domain.Completion;
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

	@GetMapping("/search/{eduId}/{completion}/{deptNo}/{year}/{quarter}")
	public ResponseEntity<List<ComDto>> searchList(
			@PathVariable("eduId") Long eduId,
			@PathVariable("completion") Character completion,
			@PathVariable("deptNo") Long deptNo,
			@PathVariable(value = "year") Long year,
			@PathVariable(value = "quarter") Long quarter) throws Exception {

		List<Long> eduIds = getEduIdsDefaltList(eduId);
		List<Character> completions = getCompletionsDefaltList(completion);
		List<Long> deptNos = getDeptNoDefaltList(deptNo);
		List<Long> quarters = getQuarterDefaltList(quarter);

		log.info("list");
		List<ComDto> comDtoList = getComDtoList(service.searchList(eduIds,completions, deptNos, year, quarters));
		return new ResponseEntity<>(comDtoList, HttpStatus.OK);
	}
	@GetMapping("/count/{eduId}/{completion}/{year}/{quarter}")
	public ResponseEntity<Long> count(
			@PathVariable("eduId") Long eduId,
			@PathVariable("completion") Character completion,
			@PathVariable(value = "year") Long year,
			@PathVariable(value = "quarter", required = false) Long quarter) throws Exception {

		List<Long> eduIds = getEduIdsDefaltList(eduId);
		List<Character> completions = getCompletionsDefaltList(completion);
		List<Long> quarters = getQuarterDefaltList(quarter);

		log.info("count");
		Long comCount = service.count(eduIds,completions,year,quarters);
		return new ResponseEntity<>(comCount, HttpStatus.OK);
	}
	@PostMapping("{eduId}/{years}/{quarters}")
	public ResponseEntity<Integer> register(
			//@RequestBody Completion completion
			@PathVariable("eduId") Long eduId,
										 @PathVariable("years") Long years,
										 @PathVariable("quarters") Long quarters
										 ) throws Exception {

		return new ResponseEntity<>(service.register(eduId,years,quarters), HttpStatus.OK);
//		return new ResponseEntity<>(service.register(completion.getEduInfo().getEduId(),completion.getYears(),completion.getQuarters()), HttpStatus.OK);
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

	private List<Long> getEduIdsDefaltList(Long eduId){
		List<Long> eduIds = new ArrayList<>();
			if(eduId==0){
				eduIds.add(1L);
				eduIds.add(2L);
				eduIds.add(3L);
				eduIds.add(4L);
				eduIds.add(5L);
			}else{
				eduIds.add(eduId);
			}
		return eduIds;
	}
	private List<Character> getCompletionsDefaltList(Character completion){
		List<Character> completions = new ArrayList<>();
			if(completion.equals('A')){
				completions.add('Y');
				completions.add('N');
			}else{
				completions.add(completion);
			}
		return completions;
	}
	private List<Long> getDeptNoDefaltList(Long deptNo){
		List<Long> deptNos = new ArrayList<>();
			if(deptNo == 0){
				deptNos.add(1L);
				deptNos.add(2L);
				deptNos.add(3L);
				deptNos.add(4L);
			}else{
				deptNos.add(deptNo);
			}
		return deptNos;
	}
	private List<Long> getQuarterDefaltList(Long quarter){
		List<Long> quarters = new ArrayList<>();
		if(quarter == 0){
			quarters.add(0L);
			quarters.add(1L);
			quarters.add(2L);
			quarters.add(3L);
			quarters.add(4L);
		}else {
			quarters.add(0L);
			quarters.add(quarter);
		}
		return quarters;
	}

}
