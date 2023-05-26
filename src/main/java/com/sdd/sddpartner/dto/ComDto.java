package com.sdd.sddpartner.dto;

import com.sdd.sddpartner.domain.Completion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ComDto {
	// Select Com 를 전달할 때, Employee의 세부정보를 은닉하기 위한 Dto

	private Long comNo;
	private Long years;
	private Long quarters;
	private Character completion;
	private String empId;
	private String name;
	private Long deptNo;
	private String deptName;
	private Long eduId;
	private String eduName;


	// Constructor to create NoticeDto object from Notice
	public ComDto(Completion completion) {
		this.comNo = completion.getComNo();
		this.years = completion.getYears();
		this.quarters = completion.getQuarters();
		this.completion = completion.getCompletion();
		this.empId = completion.getEmployee().getEmpId();
		this.name = completion.getEmployee().getName();
		this.deptNo = completion.getEmployee().getDept().getDeptNo();
		this.deptName = completion.getEmployee().getDept().getDeptName();
		this.eduId = completion.getEduInfo().getEduId();
		this.eduName = completion.getEduInfo().getName();
	}


}
