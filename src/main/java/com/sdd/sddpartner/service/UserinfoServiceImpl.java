package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Userinfo;
import com.sdd.sddpartner.repository.UserinfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserinfoServiceImpl implements UserinfoService {
    private final UserinfoRepository repository;

    @Override
    public Userinfo read(String empId) throws Exception {
        return repository.getOne(empId);
    }

    @Override
    public List<Userinfo> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "documentNo"));
    }
}
