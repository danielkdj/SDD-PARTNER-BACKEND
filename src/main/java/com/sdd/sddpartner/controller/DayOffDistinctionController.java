package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.DayOffDistinction;
import com.sdd.sddpartner.service.DayOffDistinctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/dayOffDistinctions")
public class DayOffDistinctionController {

    private final DayOffDistinctionService service;

    @PostMapping
    public ResponseEntity<DayOffDistinction> register(@Validated @RequestBody DayOffDistinction dayOffDistinction) throws Exception {
        service.register(dayOffDistinction);
        return new ResponseEntity<>(dayOffDistinction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DayOffDistinction>> list() throws Exception {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{distinctionId}")
    public ResponseEntity<DayOffDistinction> read(@PathVariable("distinctionId") Long distinctionId) throws Exception {
        DayOffDistinction dayOffDistinction = service.read(distinctionId);
        return new ResponseEntity<>(dayOffDistinction, HttpStatus.OK);
    }

    @PutMapping("/{distinctionId}")
    public ResponseEntity<DayOffDistinction> modify(@PathVariable("distinctionId") Long distinctionId, @Validated @RequestBody DayOffDistinction dayOffDistinction) throws Exception {
        dayOffDistinction.setOffCode(distinctionId);
        service.modify(dayOffDistinction);
        return new ResponseEntity<>(dayOffDistinction, HttpStatus.OK);
    }

    @DeleteMapping("/{distinctionId}")
    public ResponseEntity<Void> remove(@PathVariable("distinctionId") Long distinctionId) throws Exception {
        service.remove(distinctionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}