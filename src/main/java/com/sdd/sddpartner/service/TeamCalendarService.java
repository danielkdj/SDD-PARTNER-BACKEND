package com.sdd.sddpartner.service;
import com.sdd.sddpartner.domain.TeamCalendar;

import java.util.List;

public interface TeamCalendarService {
    public TeamCalendar readDetail(String id) throws Exception;
    public List<TeamCalendar> teamRead(int deptId) throws Exception;
    public List<TeamCalendar> list() throws Exception;
//    public void remove(Long id) throws Exception;
    public void write(TeamCalendar teamCalendar) throws Exception;

    void modify(TeamCalendar teamCalendar) throws Exception;



}
