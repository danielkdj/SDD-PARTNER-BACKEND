package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.common.security.domain.CustomUser;
import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/myinfo")
    public ResponseEntity<String> getMyInfo(
            @AuthenticationPrincipal CustomUser customUser) throws Exception {
        String username = customUser.getUsername();
        log.info("getMyInfo username = " + username);

        return new ResponseEntity<>(username, HttpStatus.OK);
    }

//    //id, permission return
//    @GetMapping("/myinfo")
//    public ResponseEntity<Employee> getMyInfo(
//            @AuthenticationPrincipal CustomUser customUser) throws Exception {
//        String username = customUser.getUsername();
//        log.info("getMyInfo username = " + username);
//        Employee employee = repository.getOne(username);
//        Employee result = new Employee();
//        result.setEmpId(employee.getEmpId());
//        result.setPermission(employee.getPermission());
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}
