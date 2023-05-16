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
@Table(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long documentNo;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 4000, nullable = false)
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @CreationTimestamp

    @Column(length = 50, nullable = false)
    private String subCategory;

}
