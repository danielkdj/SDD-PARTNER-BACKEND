package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="empId")
@Entity
@Table(name="userinfo")
public class Userinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String empId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 500, nullable = false)
    private String empImg;

    @Column
    private char marriage;

    @Column(length = 100, nullable = false)
    private String phone;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private int salary;

    @Column(length = 500, nullable = false)
    private String address;

    @Column(length = 100)
    private String empSpot;

    @Column(length = 100, nullable = false)
    private String empRank;

    @Column(length = 100)
    private String empPosition;

    @Column(nullable = false)
    private int empStatus;

    @Column(nullable = false)
    private int classification;

    @Column(nullable = false)
    private int empClassification;

    @Column(nullable = false)
    private int admission;

    @JsonFormat(pattern="yyyy-MM-dd")
    @CreationTimestamp
    private LocalDateTime hireDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @CreationTimestamp
    private LocalDateTime leaveDate;

    @Column(length = 100)
    private String reasonLeave;

    @Column(length = 100, nullable = false)
    private String isLeave;

    @Column
    private int codeLeave;

    @Column(length = 100)
    private String rewards;

    @Column(length = 100)
    private String awards;

    @Column(length = 100)
    private String qualification;

    @Column(nullable = false)
    private int annual;

    @Column(length = 20, nullable = false)
    private String permission;

    @Column(nullable = false)
    private int deptNo;

    @Column
    private int offYear;

    @Column
    private int offChildcare;

    @Column
    private int offManage;

    @Column
    private int offPragnancy;

    @Column
    private int offChildbirth;

    @Column
    private int offReward;
}
