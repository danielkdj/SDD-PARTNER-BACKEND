package com.sdd.sddpartner.service;

import java.util.List;
import com.sdd.sddpartner.domain.Attendance;

public interface AttendanceService {

    public void register(Attendance attendance) throws Exception;

    public Attendance read(Long attendanceId) throws Exception;

    public void modify(Attendance attendance) throws Exception;

    public void remove(Long attendanceId) throws Exception;

    public List<Attendance> list() throws Exception;

}