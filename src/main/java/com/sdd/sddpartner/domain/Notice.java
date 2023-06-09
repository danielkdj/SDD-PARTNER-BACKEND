package com.sdd.sddpartner.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

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
public class Notice{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long noticeNo;
	
	@Column(length = 200, nullable = false)
	private String title;
	
	@Lob
	private String content;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;



}
