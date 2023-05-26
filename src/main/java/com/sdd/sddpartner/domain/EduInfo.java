package com.sdd.sddpartner.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of="eduId")
@ToString
@Entity
@Table(name="eduInfo")
public class EduInfo {

	@Id
	private Long eduId;

	@Column(length = 50)
	private String name;

	@Column(length = 200)
	private String description;

}