package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "attendance")
public class Attendance {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id",referencedColumnName = "emp_id", nullable = false, updatable = false,insertable = false)
    private Employee employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;

    @Column(name = "EMP_ID")
    private String empId;
    @JsonFormat(pattern="HH:mm")
    @Column(name = "START_TIME")
    private Timestamp startTime;

    @JsonFormat(pattern="HH:mm")
    @Column(name = "END_TIME")
    private Timestamp endTime;

    @Column(name = "EXTRA_TIME")
    private long extraTime;

    @Column(name = "WORKING_HOUR")
    private long workingHour;

    @JsonFormat(pattern="YYYY-MM-dd")
    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    // Getters and Setters
}
