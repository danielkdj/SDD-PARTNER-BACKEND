package com.sdd.sddpartner.dto;

import com.sdd.sddpartner.domain.Employee;
import com.sdd.sddpartner.domain.Counseling;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="counId")
public class CounselingDto {

    private Long counId;
    private String empId;
    private String name;
    private Long deptNo;
    private String empSpot;
    private String empImg;
    private String counTitle;
    private String counContent;
    private String counAnswer;
    private LocalDate counAt;
    private LocalDate requestAt;
    private LocalDate requestedAt;


    public CounselingDto(Counseling c, Employee e) {
        this.counId = c.getCounId();
        this.empId = e.getEmpId();
        this.name = e.getName();
        this.deptNo = e.getDept().getDeptNo();
        this.empSpot = e.getEmpSpot();
        this.empImg = e.getEmpImg();
        this.counTitle = c.getCounTitle();
        this.counContent = c.getCounContent();
        this.counAnswer = c.getCounAnswer();
        this.counAt = c.getCounAt();
        this.requestAt = c.getRequestAt();
        this.requestedAt = c.getRequestedAt();
    }

    public static CounselingDto fromEntity(Counseling c) {
        Employee e = c.getEmployee();
        return new CounselingDto(c, e);
    }
}