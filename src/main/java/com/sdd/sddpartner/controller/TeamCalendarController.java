package com.sdd.sddpartner.controller;
import com.sdd.sddpartner.domain.TeamCalendar;
import com.sdd.sddpartner.service.TeamCalendarService;
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
@RequestMapping("/employee/teamCalendar")
public class TeamCalendarController {
    private final TeamCalendarService teamCalendarService;

    @PreAuthorize("hasPermission(#teamCalendar, 'MEMBER')")
    @GetMapping("/{id}")
    public ResponseEntity<TeamCalendar> read(@PathVariable("id") String id) throws Exception {
        TeamCalendar teamCalendar = teamCalendarService.readDetail(id);
        log.info(id);
        return new ResponseEntity<>(teamCalendar, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TeamCalendar>> list() throws Exception {
        log.info("list");
        return new ResponseEntity<>(teamCalendarService.list(), HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#teamCalendar, 'MEMBER')")
    @PostMapping
    public ResponseEntity<TeamCalendar> write(@Validated @RequestBody TeamCalendar teamCalendar) throws Exception {
        log.info("teamCalendar Write");
        teamCalendarService.write(teamCalendar);
        return new ResponseEntity<>(teamCalendar, HttpStatus.OK);
    }
}
