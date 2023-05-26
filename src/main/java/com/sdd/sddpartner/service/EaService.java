package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.dto.EaDto;

import java.util.List;

public interface EaService {
    public EaDto read(Long documentNo) throws Exception;

    public List<Ea> list() throws Exception;

    public void register(Ea ea) throws Exception;

    public void modify(Ea ea) throws Exception;

    public List<EaDto> eaApprovallist() throws Exception;

}
