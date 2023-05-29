package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Drv;
import com.sdd.sddpartner.dto.DrvDto;
import com.sdd.sddpartner.service.DrvService;
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
@RequestMapping("/drv")
public class DrvController {

	private final DrvService service;

	@GetMapping("/{drvNo}")
	public ResponseEntity<DrvDto> read(@PathVariable("drvNo") Long drvNo) throws Exception {
		DrvDto drvDto = new DrvDto(service.read(drvNo));
		return new ResponseEntity<>(drvDto, HttpStatus.OK);
	}
    @GetMapping()
	public ResponseEntity<List<DrvDto>> list() throws Exception {
		List<DrvDto> drvDtoList = getDrvDtoList(service.list());
		return new ResponseEntity<>(drvDtoList, HttpStatus.OK);
	}
	@GetMapping("/search/{car}")
	public ResponseEntity<List<DrvDto>> searchList(@PathVariable("car") String car) throws Exception {
		List<DrvDto> drvDtoList = getDrvDtoList(service.searchList(car));
		return new ResponseEntity<>(drvDtoList, HttpStatus.OK);
	}

    @PostMapping({"/{documentNo}"})
    public ResponseEntity<DrvDto> register(@PathVariable("documentNo") Long documentNo) throws Exception {
        DrvDto drvDto = new DrvDto(service.register(documentNo));

        return new ResponseEntity<>(drvDto, HttpStatus.OK);
    }
	@PatchMapping("/{drvNo}")
	public ResponseEntity<DrvDto> modify(@PathVariable("drvNo") Long drvNo,
										@RequestBody Drv drv) throws Exception {
		drv.setDrvNo(drvNo);
		service.modify(drv);

		DrvDto drvDto = new DrvDto(drv);
		return new ResponseEntity<>(drvDto, HttpStatus.OK);
	}

    //entityList를 dtoList로 변환
    private List<DrvDto> getDrvDtoList(List<Drv> drvList){
        List<DrvDto> drvDtoList = new ArrayList<>();

        for (Drv drv : drvList) {
            DrvDto drvDto = new DrvDto(drv);
            drvDtoList.add(drvDto);
        }
        return drvDtoList;
    }

}
