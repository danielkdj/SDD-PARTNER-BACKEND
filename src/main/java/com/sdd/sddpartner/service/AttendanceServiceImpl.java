package com.sdd.sddpartner.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sdd.sddpartner.domain.Attendance;
import com.sdd.sddpartner.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public void register(Attendance attendance) throws Exception {
        attendanceRepository.save(attendance);
    }

    @Override
    public Attendance read(Long attendanceId) throws Exception {
        return attendanceRepository.findById(attendanceId).orElse(null);
    }

    @Override
    public void modify(Attendance attendance) throws Exception {
        attendanceRepository.save(attendance);
    }

    @Override
    public void remove(Long attendanceId) throws Exception {
        attendanceRepository.deleteById(attendanceId);
    }

    @Override
    public List<Attendance> list() throws Exception {
        return attendanceRepository.findAll();
    }
}