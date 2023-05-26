package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Attendance;

import java.util.List;

public interface AttendanceService {

    public void register(Attendance attendance) throws Exception;

    public Attendance read(Long Id) throws Exception;

    public void modify(Attendance attendance) throws Exception;

    public void remove(Long Id) throws Exception;

    public List<Attendance> list() throws Exception;

}
