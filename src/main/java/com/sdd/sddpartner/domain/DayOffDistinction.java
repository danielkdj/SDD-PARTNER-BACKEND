package com.sdd.sddpartner.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dayoff_distinction")
public class DayOffDistinction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "off_code", nullable = false)
    private Long offCode;

    @Column(name = "off_name", nullable = false)
    private String offName;

}
