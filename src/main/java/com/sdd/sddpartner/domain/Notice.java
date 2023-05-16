package com.sdd.sddpartner.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of="noticeNo")
@ToString
@Entity
@Table(name="notice")
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_no")
	private Long noticeNo;
	
	@Column(length = 200, nullable = false)
	private String title;
	
	@Lob
	private String content;

	//employee FK
	@Column(name = "emp_id")
	private String empId;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

}
