package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "SALARY")  // DB 테이블 이름
@Entity
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_id")
    private String empId;

    @JsonFormat(pattern="yyyy-MM")
    @Column(name = "salary_date")
    private LocalDate salaryDate;

    @Column(name = "bonus")
    private Long bonus;

    @Column(name = "tot_salary")
    private Long totalSalary;

    @Column(name = "tax")
    private Long tax;

    @Column(name = "payment")
    private Long payment;


    // Add getters, setters, and any necessary methods
}