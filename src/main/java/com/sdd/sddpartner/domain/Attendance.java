package com.sdd.sddpartner.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of="comNo")
@ToString
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Long number;

    @Column(name = "emp_id")
    private String empId;

    @Column(name = "cleanup")
    private String cleanup;

    @Column(name = "salary_date")
    private Date salaryDate;

    @Column(name = "bonus")
    private Double bonus;

    @Column(name = "tot_salary")
    private Double totSalary;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "payment")
    private Double payment;

    @Column(name = "is_paid")
    private String isPaid;

    // Getters and Setters
}
