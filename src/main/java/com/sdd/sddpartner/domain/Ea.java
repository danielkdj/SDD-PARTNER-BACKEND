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
@EqualsAndHashCode(of="documentNo")
@Entity
@Table(name="ea")
public class Ea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long documentNo;

    @Column(length = 50, nullable = false)
    private String empId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 4000, nullable = false)
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private LocalDateTime endDate;

    @Column
    private int approvalStage;

    @Column
    private int approvalStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private LocalDateTime createAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private LocalDateTime middleAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private LocalDateTime completedAt;

    @Column(length = 50, nullable = false)
    private String firstApproval;

    @Column(length = 50, nullable = false)
    private String secondApproval;

    @Column
    private int categoryId;

    @Column(length = 50, nullable = false)
    private String category;

    @Column(length = 50, nullable = false)
    private String subCategory;

    @Column
    private int deptNo;

    @Column(length = 50, nullable = false)
    private String deptName;

    @Column
    private int mailNo;

    @Column(length = 50)
    private String senderEmail;

    @Column(length = 50, nullable = false)
    private String recipientEmail;
}

