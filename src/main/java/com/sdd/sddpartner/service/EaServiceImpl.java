package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.dto.EaDto;
import com.sdd.sddpartner.repository.EaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EaServiceImpl implements EaService{
    private final EaRepository repository;

    @Override
    public EaDto read(Long documentNo) throws Exception {

        Ea ea = repository.getEaOne(documentNo);

        EaDto eaDto = new EaDto(ea);

//        ea.setDocumentNo((Long)valueArray[0]);
//        ea.setEmpId((String)valueArray[1]);
//        ea.setName((String)valueArray[2]);
//        ea.setCategoryId((Long)valueArray[3]);
//        ea.setCategory((String)valueArray[4]);
//        ea.setSubCategory((String)valueArray[5]);
//        ea.setTitle((String)valueArray[6]);
//        ea.setContent((String)valueArray[7]);
//        ea.setStartDate((LocalDateTime) valueArray[8]);
//        ea.setEndDate((LocalDateTime) valueArray[9]);
//        ea.setApprovalStatus((Long)valueArray[10]);
//        ea.setCreatedAt((LocalDate) valueArray[11]);
//        ea.setDeptNo((Long)valueArray[12]);
//        ea.setDeptName((String)valueArray[13]);

        return eaDto;
    }

    @Override
    public List<Ea> list() throws Exception {
        return repository.findAll();
    }

    @Override
    public void register(Ea ea) throws Exception {
        Ea eaEntity = new Ea();

        eaEntity.setTitle(ea.getTitle());
        eaEntity.setContent(ea.getContent());
        eaEntity.setStartDate(ea.getStartDate());
        eaEntity.setEndDate(ea.getEndDate());
        eaEntity.setApprovalStatus(1L);
        eaEntity.setCreatedAt(LocalDate.now());
        eaEntity.setCategoryId(ea.getCategoryId());
        eaEntity.setEmpId(ea.getEmpId());

        repository.save(eaEntity);
    }

    @Override
    public void modify(Ea ea) throws Exception {
        Ea eaEntity = repository.getOne(ea.getDocumentNo());

        eaEntity.setTitle(ea.getTitle());
        eaEntity.setContent(ea.getContent());
        eaEntity.setStartDate(ea.getStartDate());
        eaEntity.setEndDate(ea.getEndDate());
        eaEntity.setApprovalStatus(ea.getApprovalStatus());

        repository.save(eaEntity);
    }
    public List<EaDto> eaApprovallist() throws Exception {
        List<Object[]> valueArrays = repository.findEaApproval();

        List<EaDto> eaList = new ArrayList<>();
        
        for(Object[] valueArray : valueArrays) {
            EaDto ea = new EaDto();

            ea.setDocumentNo((Long)valueArray[0]);
            ea.setEmpId((String)valueArray[1]);
            ea.setName((String)valueArray[2]);
            ea.setCategoryId((Long)valueArray[3]);
            ea.setCategory((String)valueArray[4]);
            ea.setSubCategory((String)valueArray[5]);
            ea.setTitle((String)valueArray[6]);
            ea.setContent((String)valueArray[7]);
            ea.setStartDate((LocalDateTime) valueArray[8]);
            ea.setEndDate((LocalDateTime) valueArray[9]);
            ea.setApprovalStatus((Long)valueArray[10]);
            ea.setCreatedAt((LocalDate) valueArray[11]);

            eaList.add(ea);
        }

        return eaList;
    }
}
