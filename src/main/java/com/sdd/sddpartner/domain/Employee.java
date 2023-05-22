package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties(value="hibernateLazyInitializer") 
@Data
@EqualsAndHashCode(of="empId")
@ToString
@Entity
@Table(name="employee")
public class Employee {

	@Id
	@Column(name = "emp_id", length = 50)
	private String empId;

	@ColumnDefault(" ")
	@Column(length = 100, nullable = false)
	private String name;

	@ColumnDefault(" ")
	@Column(length = 100, nullable = false)
	private String password;

	@Column
	private String empImg;

	private String empImgDisplay;

	// 주민등록번호
	@ColumnDefault(" ")
	@Column(length = 100, nullable = false)
	private String empSsn;

	@ColumnDefault("N")
	@Column(length = 100, nullable = false)
	private String gender;

	@ColumnDefault("N")
	@Column(length = 1, nullable = false)
	private String marriage;

	@ColumnDefault(" ")
	@Column(length = 100, nullable = false)
	private String phone;

	@ColumnDefault(" ")
	@Column(length = 100, nullable = false)
	private String email;

	@ColumnDefault(" ")
	@Column(nullable = false)
	private Integer salary;

	@ColumnDefault(" ")
	@Column(length = 200, nullable = false)
	private String accountNo;

	@ColumnDefault(" ")
	@Column(length = 500, nullable = false)
	private String address;

	// 직위 (사장, 이사, 부장, 과장, 대리, 사원)
	@ColumnDefault(" ")
	@Column(length = 100, nullable = false)
	private String empSpot;

	// 직책 (팀장, 실장, 본부장, 파트장)
	@ColumnDefault(" ")
	@Column(length = 100)
	private String empPosition;

	// 호봉
	@ColumnDefault(" ")
	@Column(length = 100, nullable = false)
	private String empRank;

	// 재직상태(재직, 퇴직, 퇴직예정, 휴직, 휴직예정, 입사예정, 수습)
	@ColumnDefault(" ")
	@Column(nullable = false)
	private Integer empStatus;

	// 채용구분 (공채, 특채)
	@ColumnDefault(" ")
	@Column(nullable = false)
	private Integer classification;

	// 고용구분 (정규직, 시간제, 무기계약직, 계약직, 파견직)
	@ColumnDefault(" ")
	@Column(nullable = false)
	private Integer empClassification;

	// 입사구분 (신입, 경력)
	@ColumnDefault(" ")
	@Column(nullable = false)
	private Integer admission;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private LocalDate hireDate;

	// 퇴사일
	@Column()
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveDate;

	// 퇴사사유
	@Column(length = 100)
	private String leaveReason;

	// 퇴사여부
	@ColumnDefault("N")
	@Column(length = 100, nullable = false)
	private String leaveIs;

	// 퇴사코드
	@Column()
	private Integer leaveCode;

	@Column(length = 100)
	private String awards;

	@ColumnDefault(" ")
	@Column(length = 100, nullable = false)
	private String qualifications;

	@ColumnDefault("'11'")
	@Column(length = 20)
	private String permission;

	@ColumnDefault(" ")
	@Column(nullable = false)
	private Integer deptNo;

	@Column()
	private Integer annual;

	@Column()
	private Integer offYear;

	@Column()
	private Integer offChildcare;

	@Column()
	private Integer offMarriage;

	@Column()
	private Integer offChildbirth;

	@Column()
	private Integer offReward;

	@Column()
	private Integer offPregnancy;

	@PrePersist
	@PreUpdate
	public void ensurePermissionIsNotEmpty() {
		if (permission == null || permission.isEmpty()) {
			permission = "11";
		}
	}

	/*@Column(nullable = false)
	private String coin;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private List<EmployeeAuth> authList = new ArrayList<EmployeeAuth>();

	public void addAuth(EmployeeAuth auth) {
		authList.add(auth);
	}

	public void clearAuthList() {
		authList.clear();
	}*/

}
