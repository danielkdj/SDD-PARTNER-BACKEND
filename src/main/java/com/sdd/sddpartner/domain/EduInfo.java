package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of="eduNo")
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
