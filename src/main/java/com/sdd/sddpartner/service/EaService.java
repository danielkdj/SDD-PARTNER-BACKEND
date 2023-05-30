package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.dto.EaDto;

import java.util.List;

public interface EaService {
    public Ea read(Long documentNo) throws Exception;

    public List<Ea> list() throws Exception;

    public void register(Ea ea) throws Exception;

    public void modify(Ea ea) throws Exception;

    public List<EaDto> eaApprovalList() throws Exception;

    public List<EaDto> eaAttendanceList() throws Exception;

    public List<EaDto> eaVacationList() throws Exception;

    public List<EaDto> eaAffairList() throws Exception;

    List<EaDto> eaHRList() throws Exception;

    public void modifyApproval(Ea ea);

    public void modifyReject(Ea ea);
}
