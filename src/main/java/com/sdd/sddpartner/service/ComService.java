package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Completion;

import java.util.List;

public interface ComService {

    void update(List<Long> coms) throws Exception;

    List<Completion> searchList(Completion search) throws Exception;
}
