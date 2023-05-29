package com.sdd.sddpartner.service;


import com.sdd.sddpartner.domain.Drv;

import java.util.List;

public interface DrvService {
    Drv register(Long documentNo) throws Exception;
    Drv read(Long drvNo) throws Exception;

    void modify(Drv drv) throws Exception;

    void remove(Long drvNo) throws Exception;

    List<Drv> list() throws Exception;
    List<Drv> searchList(String car) throws Exception;
}
