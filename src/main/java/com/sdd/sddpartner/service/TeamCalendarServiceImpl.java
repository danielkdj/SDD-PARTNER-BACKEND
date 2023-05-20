package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Board;
import com.sdd.sddpartner.domain.Notice;
import com.sdd.sddpartner.domain.TeamCalendar;
import com.sdd.sddpartner.repository.TeamCalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeamCalendarServiceImpl implements TeamCalendarService {

    private final TeamCalendarRepository teamCalendarRepository;
    @Override
    public TeamCalendar readDetail(String id) throws Exception {
        return teamCalendarRepository.getReferenceById(id);
    }

    @Override
    public List<TeamCalendar> teamRead(int id) throws Exception {
        return teamCalendarRepository.readTeamCalendarsByDepartmentNumber(id);
    }

    @Override
    public List<TeamCalendar> list() throws Exception {
        return teamCalendarRepository.findAll();
    }
//
//    @Override
//    public void remove(Long id) throws Exception {
//
//    }

    @Override
    public void write(TeamCalendar teamCalendar) throws Exception {
        teamCalendar.setId(String.valueOf(UUID.randomUUID()));
//        teamCalendar.setCreatedAt(LocalDateTime.now());
        teamCalendar.setModifiedAt(LocalDateTime.now());

        teamCalendarRepository.save(teamCalendar);
    }

    @Override
    public void modify(TeamCalendar teamCalendar) throws Exception {
        TeamCalendar teamCalendarEntity = teamCalendarRepository.getReferenceById(teamCalendar.getId());

        teamCalendarEntity.setTitle(teamCalendar.getTitle());
        teamCalendarEntity.setContent(teamCalendar.getContent());

        teamCalendarRepository.save(teamCalendarEntity);
    }
}
