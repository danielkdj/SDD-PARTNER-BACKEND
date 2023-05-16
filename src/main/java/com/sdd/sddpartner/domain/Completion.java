package com.sdd.sddpartner.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of="comNo")
@ToString
@Entity
@Table(name="completion")
public class Completion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "com_no")
	private Long comNo;

	//employee FK
	@Column(name = "emp_id")
	private String empId;

	//edu_info FK
	@Column(name = "edu_id")
	private String eduId;

	@Column(length = 4)
	private long years;

	@Column(length = 1)
	private long quarters;

	@Column(length = 1)
	private char completion;
}
