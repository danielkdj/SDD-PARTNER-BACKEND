package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.DayOff;
import com.sdd.sddpartner.service.DayOffService;
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
@RequestMapping("/dayOffs")
public class DayOffController {

    private final DayOffService service;

    @PostMapping
    public ResponseEntity<DayOff> register(@Validated @RequestBody DayOff dayOff) throws Exception {
        service.register(dayOff);
        return new ResponseEntity<>(dayOff, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DayOff>> list() throws Exception {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{dayOffId}")
    public ResponseEntity<DayOff> read(@PathVariable("dayOffId") Long dayOffId) throws Exception {
        DayOff dayOff = service.read(dayOffId);
        return new ResponseEntity<>(dayOff, HttpStatus.OK);
    }

    @PutMapping("/{dayOffId}")
    public ResponseEntity<DayOff> modify(@PathVariable("dayOffId") Long dayOffId, @Validated @RequestBody DayOff dayOff) throws Exception {
        dayOff.setId(dayOffId);
        service.modify(dayOff);
        return new ResponseEntity<>(dayOff, HttpStatus.OK);
    }

    @DeleteMapping("/{dayOffId}")
    public ResponseEntity<Void> remove(@PathVariable("dayOffId") Long dayOffId) throws Exception {
        service.remove(dayOffId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
