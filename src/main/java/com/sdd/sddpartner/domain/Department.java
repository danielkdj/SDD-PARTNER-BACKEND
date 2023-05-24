package com.sdd.sddpartner.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of="deptNo")
@ToString
@Entity
@Table(name="department")
public class Department {

	@Id
	private Long deptNo;

	@Column(length = 50, name = "dept_name")
	private String deptName;

	@Column(length = 50, name = "description")
	private String description;
}