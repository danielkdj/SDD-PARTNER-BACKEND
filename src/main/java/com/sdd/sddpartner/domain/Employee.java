package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties(value="hibernateLazyInitializer")
@Data
@EqualsAndHashCode(of="empId")
@ToString
@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@Id
	@Column(name = "EMP_ID", length = 50)
	private String empId;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String password;

	@Column
	private String empImg;

	@Column
	private String empImgDisplay;

	// 주민등록번호
	@Column(length = 100, nullable = false)
	private String empSsn;

	@ColumnDefault("N")
	@Column(length = 100, nullable = false)
	private String gender;

	@ColumnDefault("N")
	@Column(length = 1, nullable = false)
	private String marriage;

	@Column(length = 100, nullable = false)
	private String phone;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(nullable = false)
	private Integer salary;

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
	private Integer empStatus;

	// 채용구분 (공채, 특채)
	@Column(nullable = false)
	private Integer classification;

	// 고용구분 (정규직, 시간제, 무기계약직, 계약직, 파견직)
	@Column(nullable = false)
	private Integer empClassification;

	// 입사구분 (신입, 경력)
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

	@Column(length = 100, nullable = false)
	private String qualifications;

	@ColumnDefault("'11'")
	@Column(length = 20)
	private String permission;


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "dept_no")
	private Department dept;


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

	public void updateFrom(Employee other) {
		if (other == null) {
			throw new IllegalArgumentException("Updated employee cannot be null");
		}
		if (other.getName() != null) {
			setName(other.getName());
		}
		if (other.getPassword() != null) {
			setPassword(other.getPassword());
		}
		if (other.getEmpImg() != null) {
			setEmpImg(other.getEmpImg());
		}
		if (other.getEmpSsn() != null) {
			setEmpSsn(other.getEmpSsn());
		}
		if (other.getGender() != null) {
			setGender(other.getGender());
		}
		if (other.getMarriage() != null) {
			setMarriage(other.getMarriage());
		}
		if (other.getPhone() != null) {
			setPhone(other.getPhone());
		}
		if (other.getEmail() != null) {
			setEmail(other.getEmail());
		}
		if (other.getSalary() != null) {
			setSalary(other.getSalary());
		}
		if (other.getAccountNo() != null) {
			setAccountNo(other.getAccountNo());
		}
		if (other.getAddress() != null) {
			setAddress(other.getAddress());
		}
		if (other.getEmpSpot() != null) {
			setEmpSpot(other.getEmpSpot());
		}
		if (other.getEmpPosition() != null) {
			setEmpPosition(other.getEmpPosition());
		}
		if (other.getEmpRank() != null) {
			setEmpRank(other.getEmpRank());
		}
		if (other.getEmpStatus() != null) {
			setEmpStatus(other.getEmpStatus());
		}
		if (other.getClassification() != null) {
			setClassification(other.getClassification());
		}
		if (other.getEmpClassification() != null) {
			setEmpClassification(other.getEmpClassification());
		}
		if (other.getAdmission() != null) {
			setAdmission(other.getAdmission());
		}
		if (other.getHireDate() != null) {
			setHireDate(other.getHireDate());
		}
		if (other.getLeaveDate() != null) {
			setLeaveDate(other.getLeaveDate());
		}
		if (other.getLeaveReason() != null) {
			setLeaveReason(other.getLeaveReason());
		}
		if (other.getLeaveIs() != null) {
			setLeaveIs(other.getLeaveIs());
		}
		if (other.getLeaveCode() != null) {
			setLeaveCode(other.getLeaveCode());
		}
		if (other.getAwards() != null) {
			setAwards(other.getAwards());
		}
		if (other.getQualifications() != null) {
			setQualifications(other.getQualifications());
		}
		if (other.getPermission() != null) {
			setPermission(other.getPermission());
		}

		if (other.getDept().getDeptNo() != null) {
			this.dept.setDeptNo(other.getDept().getDeptNo());

		}
		if (other.getAnnual() != null) {
			setAnnual(other.getAnnual());
		}
		if (other.getOffYear() != null) {
			setOffYear(other.getOffYear());
		}
		if (other.getOffChildcare() != null) {
			setOffChildcare(other.getOffChildcare());
		}
		if (other.getOffMarriage() != null) {
			setOffMarriage(other.getOffMarriage());
		}
		if (other.getOffChildbirth() != null) {
			setOffChildbirth(other.getOffChildbirth());
		}
		if (other.getOffReward() != null) {
			setOffReward(other.getOffReward());
		}
		if (other.getOffPregnancy() != null) {
			setOffPregnancy(other.getOffPregnancy());
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