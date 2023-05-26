package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Salary;
import com.sdd.sddpartner.dto.SalaryDto;


import java.util.List;

public interface SalaryService {

    public void register(Salary salary) throws Exception;

    public SalaryDto read(String empId) throws Exception;

    public void modify(Salary salary) throws Exception;

    public void remove(Long salaryId) throws Exception;

    public List<SalaryDto> list() throws Exception;

 public List<SalaryDto> listWithEmp(String name) throws Exception;

}
