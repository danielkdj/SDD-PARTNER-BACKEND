package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.DayOff;
import com.sdd.sddpartner.repository.DayOffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DayOffServiceImpl implements DayOffService {

    private final DayOffRepository repository;

    @Override
    public void register(DayOff dayOff) throws Exception {
        repository.save(dayOff);
    }

    @Override
    public DayOff read(Long dayOffId) throws Exception {
        return repository.getOne(dayOffId);
    }

    @Override
    public void modify(DayOff dayOff) throws Exception {
        DayOff dayOffEntity = repository.getOne(dayOff.getOffCode());

        // Add code here to modify the fields of the dayOffEntity as needed

        repository.save(dayOffEntity);
    }

    @Override
    public void remove(Long dayOffId) throws Exception {
        repository.deleteById(dayOffId);
    }

    @Override
    public List<DayOff> list() throws Exception {
        return repository.findAll(Sort.by(Direction.DESC, "dayOffId"));
    }

}
