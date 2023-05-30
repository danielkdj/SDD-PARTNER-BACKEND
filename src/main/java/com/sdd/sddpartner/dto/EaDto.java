package com.sdd.sddpartner.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdd.sddpartner.domain.Ea;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of="documentNo")
public class EaDto {

    private Long documentNo;
    private String title;
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdAt;

    private Long approvalStatus;

    private Long categoryId;
    private String category;
    private String subCategory;

    private String empId;
    private String name;
    private Long deptNo;
    private String deptName;

    public EaDto(Ea ea) {
        this.documentNo = ea.getDocumentNo();
        this.title = ea.getTitle();
        this.content = ea.getContent();

        this.startDate = ea.getStartDate();
        this.endDate = ea.getEndDate();
        this.createdAt = ea.getCreatedAt();
        this.approvalStatus = ea.getApprovalStatus();

        this.categoryId = ea.getCategoryItem().getCategoryId();
        this.category = ea.getCategoryItem().getCategory();
        this.subCategory = ea.getCategoryItem().getSubCategory();

        this.empId = ea.getEmployee().getEmpId();
        this.name = ea.getEmployee().getName();
        this.deptNo = ea.getEmployee().getDept().getDeptNo();
        this.deptName = ea.getEmployee().getDept().getDeptName();
    }

    public EaDto(Object[] columns) {
        this.documentNo = (Long) columns[0];
        this.empId = (String) columns[1];
        this.name = (String) columns[2];
        this.categoryId = (Long) columns[3];
        this.category = (String) columns[4];
        this.subCategory = (String) columns[5];
        this.title = (String) columns[6];
        this.content = (String) columns[7];
        this.startDate = (LocalDateTime) columns[8];
        this.endDate = (LocalDateTime) columns[9];
        this.approvalStatus = (Long) columns[10];
        this.createdAt = (LocalDate) columns[11];
        this.deptNo = (Long) columns[12];
        this.deptName = (String) columns[13];
    }
}
