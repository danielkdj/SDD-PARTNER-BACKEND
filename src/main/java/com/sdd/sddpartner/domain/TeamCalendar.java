package com.sdd.sddpartner.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "TEAM_CALENDAR")
public class TeamCalendar {

    @Id
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    @Column(name = "TITLE", length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "ALLDAY", length = 1, nullable = false)
    private String allDay;

    @Column(name = "START_TIMESTAMP", nullable = false)
    private LocalDateTime start;

    @Column(name = "END_TIMESTAMP", nullable = false)
    private LocalDateTime end;

    @Column(name = "BACKGROUNDCOLOR", length = 10)
    private String backgroundColor;

    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "CREATE_AT", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "MODIFIED_AT", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "DEPT_NO", nullable = false)
    private int departmentNumber;
}