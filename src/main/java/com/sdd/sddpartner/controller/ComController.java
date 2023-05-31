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
import java.util.Arrays;
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
		List<ComDto> comDtoList = getComDtoList(
				service.searchList(eduIds,completions, deptNos, year, quarters));
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
	public ResponseEntity<List<Long>> countAll( @PathVariable(value = "year") Long year,
						@PathVariable(value = "quarter") Long quarter) throws Exception {
		List<Long> countList = new ArrayList<Long>(16);

		for(int i = 1; i<5; i++){ //1-1~4Q
			Long count1 = service.count(getEduIdsDefaltList(1L),
					getCompletionsDefaltList('A'),year,getQuarterDefaltList((long) i));
			countList.add(count1);
		}
		for(int i = 2; i<6; i++){ //2~5 0Q
			Long count1 = service.count(getEduIdsDefaltList((long)i),
					getCompletionsDefaltList('A'),year,getQuarterDefaltList(0L));
			countList.add(count1);
		}
		for(int i = 1; i<5; i++){ //1-1~4Q
			Long count1 = service.count(getEduIdsDefaltList(1L),
					getCompletionsDefaltList('Y'),year,getQuarterDefaltList((long)i));
			countList.add(count1);
		}
		for(int i = 2; i<6; i++){ //2~5 0Q
			Long count1 = service.count(getEduIdsDefaltList((long)i),
					getCompletionsDefaltList('Y'),year,getQuarterDefaltList(0L));
			countList.add(count1);
		}
		return new ResponseEntity<>(countList, HttpStatus.OK);
	}

	@PostMapping("/{eduId}/{year}/{quarter}")
	public ResponseEntity<List<Completion>> register(
			@PathVariable("eduId") Long eduId,
			@PathVariable("year") Long year,
			@PathVariable("quarter") Long quarter) throws Exception {

		log.info("completion register: "+ eduId.toString(),year.toString(),quarter.toString());
		return new ResponseEntity<>(service.register(eduId,year,quarter), HttpStatus.OK);
	}

	@PatchMapping("/{comNo}")
	public ResponseEntity<Completion> modify(
			@PathVariable("comNo") Long comNo) throws Exception {
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
		List<Long> eduIds;
		if(eduId==0){
			eduIds = Arrays.asList(1L,2L,3L,4L,5L);
		}else{
			eduIds = Arrays.asList(eduId);
		}
		return eduIds;
	}

	private List<Character> getCompletionsDefaltList(Character completion){
		List<Character> completions;
		if(completion.equals('A')){
			completions = Arrays.asList('Y','N');
		}else{
			completions = Arrays.asList(completion);
		}
		return completions;
	}

	private List<Long> getDeptNoDefaltList(Long deptNo){
		List<Long> deptNos;
		if(deptNo == 0){
			deptNos = Arrays.asList(0L,1L,2L,3L,4L);
		}else{
			deptNos = Arrays.asList(deptNo);
		}
		return deptNos;
	}

	private List<Long> getQuarterDefaltList(Long quarter){
		List<Long> quarters;
		if(quarter == 0){
			quarters = Arrays.asList(0L,1L,2L,3L,4L);
		}else {
			quarters = Arrays.asList(0L,quarter);
		}
		return quarters;
	}

}
