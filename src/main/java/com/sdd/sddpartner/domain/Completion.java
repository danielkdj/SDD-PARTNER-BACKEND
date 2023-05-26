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
	private Long comNo;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name= "emp_id")
	private Employee employee;

//	private String empId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name= "edu_id")
	private EduInfo eduInfo;
	
	private Long years;
	private Long quarters;

	@Column(length = 1)
	private Character completion;

}
