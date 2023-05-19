package com.sdd.sddpartner.service;

import java.util.List;
import com.sdd.sddpartner.domain.DayOff;

public interface DayOffService {

    public void register(DayOff dayOff) throws Exception;

    public DayOff read(Long dayOffId) throws Exception;

    public void modify(DayOff dayOff) throws Exception;

    public void remove(Long dayOffId) throws Exception;

    public List<DayOff> list() throws Exception;

}
