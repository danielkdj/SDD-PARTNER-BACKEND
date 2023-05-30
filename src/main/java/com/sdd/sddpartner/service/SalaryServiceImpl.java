package com.sdd.sddpartner.service;


import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.domain.Salary;

import com.sdd.sddpartner.dto.SalaryDto;
import com.sdd.sddpartner.repository.EmployeeRepository;
import com.sdd.sddpartner.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository repository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void register(Salary salary) throws Exception {
        repository.save(salary);
    }



    @Override
    public SalaryDto read(String empId) throws Exception {
        Salary salary = repository.findTopByEmpIdOrderBySalaryDateDesc(empId);


        return SalaryDto.fromEntity(salary);
    }

    @Override
    public void modify(Salary salary) throws Exception {
        repository.findById(salary.getId())
                .ifPresentOrElse(salaryEntity -> {
                    salaryEntity.setBonus(salary.getBonus());
                    salaryEntity.setTotalSalary(salary.getTotalSalary());
                    salaryEntity.setSalaryDate(salary.getSalaryDate());
                    salaryEntity.setTax(salary.getTax());
                    salaryEntity.setPayment(salary.getPayment());
                    // save 메서드 호출 없음
                }, () -> {
                    throw new NoSuchElementException("Salary with id " + salary.getId() + " not found");
                });
    }

    @Override
    public void remove(Long salaryId) throws Exception {
        repository.deleteById(salaryId);
    }


    @Override
    public List<SalaryDto> list() throws Exception {
        List<Salary> salaries = repository.findAll();

        return salaries.stream()
                .map(SalaryDto::fromEntity)
                .collect(Collectors.toList());
    }
    public List<SalaryDto> listWithEmp(String name) {
        List<Employee> employees = employeeRepository.findByName(name);
        List<SalaryDto> result = new ArrayList<>();

        for(Employee employee : employees) {
            List<Salary> salaries = repository.findAllByEmpId(employee.getEmpId());
            List<SalaryDto> salaryDtos = salaries.stream()
                    .map(SalaryDto::fromEntity)
                    .collect(Collectors.toList());
            result.addAll(salaryDtos);
        }
        return result;
    }




}
