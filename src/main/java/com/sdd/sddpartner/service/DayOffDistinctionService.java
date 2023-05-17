package com.sdd.sddpartner.service;

import java.util.List;
import com.sdd.sddpartner.domain.DayOffDistinction;

public interface DayOffDistinctionService {

    public void register(DayOffDistinction dayOffDistinction) throws Exception;

    public DayOffDistinction read(Long dayOffDistinctionId) throws Exception;

    public void modify(DayOffDistinction dayOffDistinction) throws Exception;

    public void remove(Long dayOffDistinctionId) throws Exception;

    public List<DayOffDistinction> list() throws Exception;

}