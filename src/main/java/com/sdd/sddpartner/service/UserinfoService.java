package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Userinfo;

import java.util.List;

public interface UserinfoService {
    public Userinfo read(String empId) throws Exception;

    public List<Userinfo> list() throws Exception;
}
