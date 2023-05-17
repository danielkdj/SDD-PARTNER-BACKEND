package com.sdd.sddpartner.service;

import java.util.List;
import com.sdd.sddpartner.domain.Salary;

public interface SalaryService {

    public void register(Salary salary) throws Exception;

    public Salary read(Long salaryId) throws Exception;

    public void modify(Salary salary) throws Exception;

    public void remove(Long salaryId) throws Exception;

    public List<Salary> list() throws Exception;

}
