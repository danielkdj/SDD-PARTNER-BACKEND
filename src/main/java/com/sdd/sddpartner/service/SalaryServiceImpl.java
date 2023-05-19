package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Salary;
/*import com.sdd.sddpartner.dto.SalaryDto;*/
import com.sdd.sddpartner.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository repository;
    /*private final EmployeeRepository employeeRepository;*/

    @Override
    public void register(Salary salary) throws Exception {
        repository.save(salary);
    }

    @Override
    public Salary read(Long salaryId) throws Exception {
        return repository.getOne(salaryId);
    }

    @Override
    public void modify(Salary salary) throws Exception {
        Salary salaryEntity = repository.getOne(salary.getId());

        // Add code here to modify the fields of the salaryEntity as needed

        repository.save(salaryEntity);
    }

    @Override
    public void remove(Long salaryId) throws Exception {
        repository.deleteById(salaryId);
    }

    @Override
    public List<Salary> list() throws Exception {
        return repository.findAllByOrderByIdDesc();
    }

/*    @Override
    public List<SalaryDto> listWithEmp() throws Exception {
        List<Salary> salaries = repository.findAllByOrderByIdDesc();
        List<SalaryDto> dtoList = new ArrayList<>();
        for (Salary salary : salaries) {
            Employee employee = employeeRepository.findById(salary.getEmpId()).orElse(null);
            if (employee != null) {
                dtoList.add(new SalaryDto(salary, employee));
            }
        }
        return dtoList;
    }*/

}