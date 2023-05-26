package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.DayOffDistinction;
import com.sdd.sddpartner.repository.DayOffDistinctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DayOffDistinctionServiceImpl implements DayOffDistinctionService {

    private final DayOffDistinctionRepository repository;

    @Override
    public void register(DayOffDistinction dayOffDistinction) throws Exception {
        repository.save(dayOffDistinction);
    }

    @Override
    public DayOffDistinction read(Long dayOffDistinctionId) throws Exception {
        return repository.getOne(dayOffDistinctionId);
    }

    @Override
    public void modify(DayOffDistinction dayOffDistinction) throws Exception {
        DayOffDistinction dayOffDistinctionEntity = repository.getOne(dayOffDistinction.getOffCode());

        // Add code here to modify the fields of the dayOffDistinctionEntity as needed

        repository.save(dayOffDistinctionEntity);
    }

    @Override
    public void remove(Long dayOffDistinctionId) throws Exception {
        repository.deleteById(dayOffDistinctionId);
    }

    @Override
    public List<DayOffDistinction> list() throws Exception {
        return repository.findAll(Sort.by(Direction.DESC, "dayOffDistinctionId"));
    }

}
