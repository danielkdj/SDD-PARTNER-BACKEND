package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Attendance;
import com.sdd.sddpartner.service.AttendanceService;
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
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService service;

    @PostMapping
    public ResponseEntity<Attendance> register(@Validated @RequestBody Attendance attendance) throws Exception {
        service.register(attendance);
        return new ResponseEntity<>(attendance, HttpStatus.CREATED);
    }

    @GetMapping("/List")
    public ResponseEntity<List<Attendance>> list() throws Exception {

        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/id/{attendanceId}")
    public ResponseEntity<Attendance> read(@PathVariable("attendanceId") Long attendanceId) throws Exception {
        Attendance attendance = service.read(attendanceId);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @PutMapping("/id/{attendanceId}")
    public ResponseEntity<Attendance> modify(@PathVariable("attendanceId") Long attendanceId, @Validated @RequestBody Attendance attendance) throws Exception {
        attendance.setId(attendanceId);
        service.modify(attendance);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @DeleteMapping("/id/{attendanceId}")
    public ResponseEntity<Void> remove(@PathVariable("attendanceId") Long attendanceId) throws Exception {
        service.remove(attendanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}