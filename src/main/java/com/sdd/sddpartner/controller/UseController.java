package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.domain.SearchCategoryDate;
import com.sdd.sddpartner.service.UseService;
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
@RequestMapping("/use")
public class UseController {

    private final UseService service;

    @GetMapping("/{documentNo}")
    public ResponseEntity<Ea> select(@PathVariable("documentNo") Long documentNo) throws Exception {
        Ea use = service.select(documentNo);

        return new ResponseEntity<>(use, HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Ea>> categoryList(@PathVariable("category") long category) throws Exception{
        log.info("list" + category);
        return new ResponseEntity<>(service.categoryList(category), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Ea>> searchList(@RequestBody SearchCategoryDate search) throws Exception {
        log.info("searchList");

        return new ResponseEntity<>(service.searchList(search), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{documentNo}/{approve}")
    public ResponseEntity<Ea> update(@PathVariable("documentNo") Long documentNo, @Validated @RequestBody long approve) throws Exception {
        Ea ea = new Ea();
        ea.setDocumentNo(documentNo);
        ea.setApprovalStats(approve);
        service.update(ea);

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }

}
