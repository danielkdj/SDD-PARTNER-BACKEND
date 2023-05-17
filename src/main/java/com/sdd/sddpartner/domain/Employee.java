package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value="hibernateLazyInitializer") 
@Data
@EqualsAndHashCode(of="empId")
@ToString
@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private String empId;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String password;

	@Column(length = 100, nullable = false)
	private String empImg;

	// 주민등록번호
	@Column(length = 100, nullable = false)
	private String empSSN;

	@Column(length = 100, nullable = false)
	private String gender;

	@Column(length = 1, nullable = false)
	private String marriage;

	@Column(length = 100, nullable = false)
	private String phone;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(nullable = false)
	private int salary;

	@Column(length = 200, nullable = false)
	private String accountNo;

	@Column(length = 500, nullable = false)
	private String address;

	// 직위 (사장, 이사, 부장, 과장, 대리, 사원)
	@Column(length = 100, nullable = false)
	private String empSpot;

	// 직책 (팀장, 실장, 본부장, 파트장)
	@Column(length = 100)
	private String empPosition;

	// 호봉
	@Column(length = 100, nullable = false)
	private String empRank;

	// 재직상태(재직, 퇴직, 퇴직예정, 휴직, 휴직예정, 입사예정, 수습)
	@Column(nullable = false)
	private int empStatus;

	// 채용구분 (공채, 특채)
	@Column(nullable = false)
	private int classification;

	// 고용구분 (정규직, 시간제, 무기계약직, 계약직, 파견직)
	@Column(nullable = false)
	private int empClassification;

	// 입사구분 (신입, 경력)
	@Column(nullable = false)
	private int admission;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime hireDate;

	// 퇴사일
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private LocalDateTime leaveDate;

	// 퇴사사유
	@Column(length = 100)
	private String leaveReason;

	// 퇴사여부
	@Column(length = 1, nullable = false)
	private String leaveIs;

	// 퇴사코드
	@Column
	private int leaveCode;

	@Column(length = 100)
	private String awards;

	@Column(length = 100)
	private String qualifications;

	@Column(length = 20, nullable = false)
	private String permission;

	@Column(nullable = false)
	private int deptNo;

	@Column(nullable = false)
	private int annual;

	@Column
	private int offYear;

	@Column
	private int offChildcare;

	@Column
	private int offMarriage;

	@Column
	private int offPregnancy;

	@Column
	private int offChildbirth;

	@Column
	private int offReward;

	private String coin;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private List<EmployeeAuth> authList = new ArrayList<EmployeeAuth>();

	public void addAuth(EmployeeAuth auth) {
		authList.add(auth);
	}

	public void clearAuthList() {
		authList.clear();
	}

}
