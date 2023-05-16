package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.domain.Userinfo;
import com.sdd.sddpartner.service.EaService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ea")
public class EaController {

    private final EaService service;
    @GetMapping("/{documentNo}")
    public ResponseEntity<Ea> read(@PathVariable("documentNo") Long documentNo) throws Exception {
        Ea ea = service.read(documentNo);

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Ea>> list() throws Exception {
        log.info("list");

        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EA')")
    @PostMapping
    public ResponseEntity<Ea> createEA(@Validated @RequestBody Ea ea,
                                     @AuthenticationPrincipal Userinfo userinfo) throws Exception {


        String empId = userinfo.getEmpId();

        log.info("register empId = " + empId);

        ea.setName(empId);

        service.createEA(ea);

        log.info("register ea.getDocumentNo() = " + ea.getDocumentNo());

        ea = service.read(ea.getDocumentNo());

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EA')")
    @PutMapping("/{documentNo}")
    public ResponseEntity<Ea> modify(@PathVariable("documentNo") Long documentNo,
                                     @Validated @RequestBody Ea ea) throws Exception {
        ea.setDocumentNo(documentNo);
        service.modify(ea);

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }

}
