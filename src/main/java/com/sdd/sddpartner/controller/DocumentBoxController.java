package com.sdd.sddpartner.controller;

import com.sdd.sddpartner.domain.DocumentBox;
import com.sdd.sddpartner.domain.TeamCalendar;
import com.sdd.sddpartner.service.DocumentBoxService;
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
@RequestMapping("/document")
public class DocumentBoxController {
    private final DocumentBoxService documentBoxService;

    @GetMapping
    public ResponseEntity<List<DocumentBox>> list() throws Exception {
        log.info("list");
        return new ResponseEntity<>(documentBoxService.list(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") String id) throws Exception {
        log.info("remove");
        documentBoxService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<DocumentBox> write(@Validated @RequestBody DocumentBox documentBox) throws Exception {
        log.info("teamCalendar Write");
        documentBoxService.write(documentBox);
        return new ResponseEntity<>(documentBox, HttpStatus.OK);
    }
}
