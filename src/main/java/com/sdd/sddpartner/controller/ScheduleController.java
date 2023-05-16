package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Schedule;
import com.sdd.sddpartner.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService service;
    @GetMapping("/{documentNo}")
    public ResponseEntity<Schedule> read(@PathVariable("documentNo") Long documentNo) throws Exception {
        Schedule schedule = service.read(documentNo);

        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> list() throws Exception {
        log.info("list");

        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }
}
