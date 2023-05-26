package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EaDto {

    private Long documentNo;

    private String title;

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    private Long approvalStatus;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdAt;

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

}
