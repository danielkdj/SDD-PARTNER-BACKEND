package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Schedule;
import com.sdd.sddpartner.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository repository;

    @Override
    public Schedule read(Long documentNo) throws Exception {
        return repository.getOne(documentNo);
    }

    @Override
    public List<Schedule> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "documentNo"));
    }
}
