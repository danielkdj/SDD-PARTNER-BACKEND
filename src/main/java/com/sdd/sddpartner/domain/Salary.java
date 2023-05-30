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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id",referencedColumnName = "emp_id", nullable = false, updatable = false, insertable = false)
    private Employee employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMP_ID")
    private String empId;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "SALARY_DATE")
    private LocalDate salaryDate;

    @Column(name = "BONUS")
    private Long bonus;

    @Column(name = "TOT_SALARY")
    private Long totalSalary;

    @Column(name = "TAX")
    private Long tax;

    @Column(name = "PAYMENT")
    private Long payment;



    // Add getters, setters, and any necessary methods
}
