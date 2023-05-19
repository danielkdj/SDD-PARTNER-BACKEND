package com.sdd.sddpartner.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "DAYOFF")
public class DayOff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dayoff_sequence_generator")
    @SequenceGenerator(name = "dayoff_sequence_generator", sequenceName = "DAYOFF_SEQUENCE", allocationSize = 1)
    private Long id;

    @Column(name = "EMP_ID")
    private String empId;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "OFF_CODE")
    private Long offCode;
}