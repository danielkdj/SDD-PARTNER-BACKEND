package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.DocumentBox;

import java.util.List;

public interface DocumentBoxService {

    public List<DocumentBox> list() throws Exception;
    public void remove(Long id) throws Exception;
}
