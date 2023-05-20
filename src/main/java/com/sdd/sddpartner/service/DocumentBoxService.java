package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Board;
import com.sdd.sddpartner.domain.DocumentBox;
import com.sdd.sddpartner.domain.TeamCalendar;

import java.util.List;

public interface DocumentBoxService {

    public List<DocumentBox> list() throws Exception;
    public void remove(String id) throws Exception;
    public void write(DocumentBox documentBox) throws Exception;
    public void modify(DocumentBox documentBox) throws Exception;
}
