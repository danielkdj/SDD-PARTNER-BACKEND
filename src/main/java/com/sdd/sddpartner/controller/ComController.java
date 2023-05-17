package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Completion;
import com.sdd.sddpartner.service.ComService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/com")
public class ComController {

    private final ComService service;

    @PostMapping
    public ResponseEntity<List<Completion>> searchList(@RequestBody Completion search) throws Exception {
        log.info("searchList");

        return new ResponseEntity<>(service.searchList(search), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Void> update(@Validated @RequestBody List<Long> coms) throws Exception {
        service.update(coms);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
