package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class NoticeDto {
	// Select Notice 를 전달할 때, Employee의 세부정보를 은닉하기 위한 Dto

    private Long noticeNo;
	private String title;
	private String content;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	private String empId;
	private String name;
	private Long deptNo;
	private String deptName;

	// Constructor to create NoticeDto object from Notice
	public NoticeDto(Notice notice) {
		this.noticeNo = notice.getNoticeNo();
		this.title = notice.getTitle();
		this.content = notice.getContent();
		this.createdAt = notice.getCreatedAt();
		this.empId = notice.getEmployee().getEmpId();
		this.name = notice.getEmployee().getName();
		this.deptNo = notice.getEmployee().getDept().getDeptNo();
		this.deptName = notice.getEmployee().getDept().getDeptName();
	}


}
