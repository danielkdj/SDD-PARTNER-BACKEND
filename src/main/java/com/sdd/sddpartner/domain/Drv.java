package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of="drvNo")
@ToString
@Entity
@Table(name="DRV")
public class Drv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drvNo;

    private String driver;
    private String reason;
    private String deptName;
    private String car;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "drv_start")
    private LocalDateTime drvStart;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "drv_end")
    private LocalDateTime drvEnd;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @Column(name = "drv_return")
    private LocalDateTime drvReturn;

    private Long beforeMileage;
    private Long afterMileage;

    private Long documentNo;

    public Drv(Ea ea){
        //this.ea = ea;
        this.documentNo = ea.getDocumentNo();
        this.driver = ea.getEmployee().getName();
        this.deptName = ea.getEmployee().getDept().getDeptName();
        this.car = ea.getCategoryItem().getSubCategory();
        this.reason = ea.getTitle();
        this.drvStart = ea.getStartDate();
        this.drvEnd = ea.getEndDate();
    }

    public Drv() {
    }
}
