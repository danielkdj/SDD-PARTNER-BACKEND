package com.sdd.sddpartner.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.domain.Salary;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SalaryDto {

    private Long id;
    private String empId;
    private String employeeName;
    private Long deptNo;
    private String empPosition;
    private String empImg;

    @JsonFormat(pattern="yyyy-MM")
    private LocalDate salaryDate;
    private Long bonus;
    private Long totalSalary;
    private Long tax;
    private Long payment;

    // Constructor to create SalaryDto object from Salary and Employee
    public SalaryDto(Salary salary, Employee employee) {
        this.id = salary.getId();
        this.empId = employee.getEmpId();
        this.employeeName = employee.getName();
        this.deptNo = employee.getDept().getDeptNo();
        this.empPosition = employee.getEmpPosition();
        this.empImg = employee.getEmpImg();
        this.salaryDate = salary.getSalaryDate();
        this.bonus = salary.getBonus();
        this.totalSalary = salary.getTotalSalary();
        this.tax = salary.getTax();
        this.payment = salary.getPayment();
    }

    // Static factory method
    public static SalaryDto fromEntity(Salary salary) {
        Employee employee = salary.getEmployee();
        return new SalaryDto(salary, employee);
    }
}
