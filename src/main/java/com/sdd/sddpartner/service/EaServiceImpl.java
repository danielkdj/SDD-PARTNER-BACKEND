package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.repository.EaRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EaServiceImpl implements EaService{
    private final EaRepository repository;

    @Override
    public Ea read(Long documentNo) throws Exception {
        return repository.getOne(documentNo);
    }

    @Override
    public List<Ea> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "documentNo"));
    }

    @Override
    public Ea createEA(Ea ea) throws Exception {
        return repository.save(ea);
    }

    @Override
    public void modify(Ea ea) throws Exception {
        Ea eaEntity = repository.getOne(ea.getDocumentNo());

        eaEntity.setTitle(ea.getTitle());
        eaEntity.setContent(ea.getContent());

        repository.save(eaEntity);
    }
}
