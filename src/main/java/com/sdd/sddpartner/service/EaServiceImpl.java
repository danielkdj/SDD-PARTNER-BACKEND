package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.dto.EaDto;
import com.sdd.sddpartner.repository.EaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sdd.sddpartner.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EaServiceImpl implements EaService{

    private final EaRepository repository;

    private final EmployeeRepository empRepository;

    @Override
    public Ea read(Long documentNo) throws Exception {

        Ea ea = repository.getOne(documentNo);

        return ea;
    }

    @Override
    public List<Ea> list() throws Exception {
        return repository.findAll();
    }

    @Override
    public void register(Ea ea) throws Exception {
       String empId =  ea.getEmployee().getEmpId();

       Employee emp = empRepository.getOne(empId);

       ea.setEmployee(emp);

       ea.setCreatedAt(LocalDate.now());
//        Ea eaEntity = new Ea();
//
//        eaEntity.setTitle(ea.getTitle());
//        eaEntity.setContent(ea.getContent());
//        eaEntity.setStartDate(ea.getStartDate());
//        eaEntity.setEndDate(ea.getEndDate());
//        eaEntity.setApprovalStatus(1L);
//        eaEntity.setCreatedAt(LocalDate.now());
//        eaEntity.getCategoryItem().setCategoryId(ea.getCategoryItem().getCategoryId());
//        eaEntity.getEmployee().setEmpId(ea.getEmployee().getEmpId());

        repository.save(ea);
    }

    @Override
    public void modify(Ea ea) throws Exception {
        Ea eaEntity = repository.getOne(ea.getDocumentNo());

        eaEntity.setTitle(ea.getTitle());
        eaEntity.setContent(ea.getContent());
        eaEntity.setStartDate(ea.getStartDate());
        eaEntity.setEndDate(ea.getEndDate());
        eaEntity.setApprovalStatus(1L);
        eaEntity.setCreatedAt(LocalDate.now());
        eaEntity.getCategoryItem().setCategoryId(ea.getCategoryItem().getCategoryId());
        eaEntity.getEmployee().setEmpId(ea.getEmployee().getEmpId());

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

    @Override
    public void modifyApproval(Ea ea) {
        Ea eaEntity = repository.getOne(ea.getDocumentNo());

//        eaEntity.setTitle(ea.getTitle());
//        eaEntity.setContent(ea.getContent());
//        eaEntity.setCreatedAt(ea.getCreatedAt());
        eaEntity.setApprovalStatus(2L);
//        eaEntity.setStartDate(ea.getStartDate());
//        eaEntity.setEndDate(ea.getEndDate());

        repository.save(eaEntity);
    }

    @Override
    public void modifyReject(Ea ea) {
        Ea eaEntity = repository.getOne(ea.getDocumentNo());

//        eaEntity.setDocumentNo(ea.getDocumentNo());
//        eaEntity.setTitle(ea.getTitle());
//        eaEntity.setContent(ea.getContent());
//        eaEntity.setCreatedAt(ea.getCreatedAt());
        eaEntity.setApprovalStatus(3L);
//        eaEntity.setStartDate(ea.getStartDate());
//        eaEntity.setEndDate(ea.getEndDate());

        repository.save(eaEntity);
    }
}
