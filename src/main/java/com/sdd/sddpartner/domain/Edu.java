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
@Table(name="edu")
public class Edu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "edu_no")
	private Long eduNo;

	//edu_info FK
	@Column(name = "edu_id")
	private Long eduId;

	//employee FK
	@Column(name = "emp_id")
	private String empId;

	@JsonFormat(pattern="yyyy-MM-dd")
	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(length = 200, nullable = false)
	private String title;

	@Column(length = 200)
	private String institution;

	@Column(length = 50)
	private String presenter;

	@Column(length = 200)
	private String place;

	@Lob
	private String content;

	private long count;

	@Column(name = "edu_start")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime eduStart;

	@Column(name = "edu_end")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime eduEnd;

}
