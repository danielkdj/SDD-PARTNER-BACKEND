package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Salary;
import com.sdd.sddpartner.service.SalaryService;
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
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryService service;

    @PostMapping
    public ResponseEntity<Salary> register(@Validated @RequestBody Salary salary) throws Exception {
        service.register(salary);
        return new ResponseEntity<>(salary, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Salary>> list() throws Exception {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/id/{salaryId}")
    public ResponseEntity<Salary> read(@PathVariable("salaryId") Long salaryId) throws Exception {
        Salary salary = service.read(salaryId);
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }

    @PutMapping("/id/{salaryId}")
    public ResponseEntity<Salary> modify(@PathVariable("salaryId") Long salaryId, @Validated @RequestBody Salary salary) throws Exception {
        salary.setId(salaryId);
        service.modify(salary);
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }

    @DeleteMapping("/id/{salaryId}")
    public ResponseEntity<Void> remove(@PathVariable("salaryId") Long salaryId) throws Exception {
        service.remove(salaryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
