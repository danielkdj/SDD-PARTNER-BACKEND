package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Member;
import com.sdd.sddpartner.domain.Userinfo;
import com.sdd.sddpartner.service.UserinfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {
    private final UserinfoService service;

//    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{empId}")
    public ResponseEntity<Userinfo> read(@PathVariable("empId") String empId) throws Exception {
        Userinfo userinfo = service.read(empId);

        return new ResponseEntity<>(userinfo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Userinfo>> list() throws Exception {
        log.info("list");

        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

//    @PostMapping(value = "/setup", produces="text/plain;charset=UTF-8")
//    public ResponseEntity<String> setupPassword(@Validated @RequestBody Employee emp) throws Exception {
//        log.info("setupAEmployee : emp.getName() = " + emp.getName());
//
//        String inputPassword = emp.getPassword();
//        emp.setPassword(passwordEncoder.encode(inputPassword));
//
//            service.setupPassword(emp);
//
//            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
//    }
}
