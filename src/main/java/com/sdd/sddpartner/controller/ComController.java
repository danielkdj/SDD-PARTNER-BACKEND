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
	@GetMapping("/countAll/{year}/{quarter}")
	public ResponseEntity<List<Long>> countAll(
			@PathVariable(value = "year") Long year,
			@PathVariable(value = "quarter") Long quarter) throws Exception {
		log.info("count");
		List<Long> countList = new ArrayList<Long>(16);

		//1-1~4Q
		for(int i = 1; i<5; i++){
		Long count1 = service.count(getEduIdsDefaltList(1L),getCompletionsDefaltList('A'),year,getQuarterDefaltList(Long.valueOf(i)));
		countList.add(count1);
		}
		//2~5 0Q
		for(int i = 2; i<6; i++){
		Long count1 = service.count(getEduIdsDefaltList(Long.valueOf(i)),getCompletionsDefaltList('A'),year,getQuarterDefaltList(0L));
		countList.add(count1);
		}
		//1-1~4Q
		for(int i = 1; i<5; i++){
		Long count1 = service.count(getEduIdsDefaltList(1L),getCompletionsDefaltList('Y'),year,getQuarterDefaltList(Long.valueOf(i)));
		countList.add(count1);
		}
		//2~5 0Q
		for(int i = 2; i<6; i++){
		Long count1 = service.count(getEduIdsDefaltList(Long.valueOf(i)),getCompletionsDefaltList('Y'),year,getQuarterDefaltList(0L));
		countList.add(count1);
		}


		return new ResponseEntity<>(countList, HttpStatus.OK);
	}
	@PostMapping("/{eduId}/{years}/{quarters}")
	public ResponseEntity<List<Completion>> register(
			@PathVariable("eduId") Long eduId,
		 	@PathVariable("years") Long years,
			@PathVariable("quarters") Long quarters) throws Exception {

		log.info("completion register: "+ eduId.toString(),years.toString(),quarters.toString());
		return new ResponseEntity<>(service.register(eduId,years,quarters), HttpStatus.OK);
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
