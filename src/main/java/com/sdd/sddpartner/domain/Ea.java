package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name="EA")
public class Ea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCUMENT_NO")
    private Long documentNo;

    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(nullable = false, name = "TITLE")
    private String title;

    @Column(nullable = false, name = "CONTENT")
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "APPROVAL_STATUS")
    private Long approvalStatus;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "CREATED_AT")
    private LocalDate createdAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false, nullable = false)
    private CategoryItem categoryItem;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "EMP_ID", insertable = false, updatable = false, nullable = false)
    private Employee employee;
}
