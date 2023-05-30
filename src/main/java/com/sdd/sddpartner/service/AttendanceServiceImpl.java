package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Attendance;
import com.sdd.sddpartner.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public void register(Attendance attendance) throws Exception {
        attendanceRepository.save(attendance);
    }

    @Override
    public Attendance read(Long Id) throws Exception {
        return attendanceRepository.findById(Id).orElse(null);
    }

    @Override
    public void modify(Attendance attendance) throws Exception {
        attendanceRepository.save(attendance);
    }

    @Override
    public void remove(Long Id) throws Exception {
        attendanceRepository.deleteById(Id);
    }

    @Override
    public List<Attendance> list() throws Exception {

        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> listEmp(String empId) throws Exception {

        return attendanceRepository.findAllByEmpId(empId);
    }
}
