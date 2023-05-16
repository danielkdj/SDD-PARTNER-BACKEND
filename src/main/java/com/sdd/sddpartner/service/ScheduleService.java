package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    public Schedule read(Long documentNo) throws Exception;

    public List<Schedule> list() throws Exception;
}
