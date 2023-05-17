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
	@Column(name = "edu_id", nullable = false)
	private Long eduId;

	@Column(nullable = false)
	private String name;

	@Lob
	@Column(nullable = false)
	private String description;

}
