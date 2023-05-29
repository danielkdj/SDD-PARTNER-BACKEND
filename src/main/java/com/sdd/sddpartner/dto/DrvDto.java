package com.sdd.sddpartner.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdd.sddpartner.domain.Drv;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DrvDto {
	// Select Drv 를 전달할 때, Ea의 세부정보를 은닉하기 위한 Dto
	private Long drvNo;
	private String driver;
	private String reason;
	private String deptName;
	private String car;
	private Long documentNo;
	private Long status;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime drvStart;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime drvEnd;
	private Long beforeMileage;
	private Long afterMileage;
	private Long actualMileage;


	// Constructor to create DrvDto object from Drv
	public DrvDto(Drv drv) {
		this.drvNo = drv.getDrvNo();
		this.driver = drv.getDriver();
		this.reason = drv.getReason();
		this.deptName = drv.getDeptName();
		this.car = drv.getCar();
		this.documentNo = drv.getDocumentNo();
		this.drvStart = drv.getDrvStart();
		this.drvEnd = drv.getDrvEnd();

		this.beforeMileage = drv.getBeforeMileage();
		this.afterMileage = drv.getAfterMileage();

		if(this.beforeMileage != null && this.afterMileage != null ){
			this.actualMileage = afterMileage-beforeMileage;
		}
	}


}
