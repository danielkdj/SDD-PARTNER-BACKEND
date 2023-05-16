package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;

import java.util.List;

public interface EaService {
    public Ea read(Long documentNo) throws Exception;

    public List<Ea> list() throws Exception;

    public Ea createEA(Ea ea) throws Exception;

    public void modify(Ea ea) throws Exception;
}
