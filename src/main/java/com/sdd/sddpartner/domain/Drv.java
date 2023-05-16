package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of="drvNo")
@ToString
@Entity
@Table(name="drv")
public class Drv {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long drvNo;

	//Ea FK
	@Column(name="document_no", nullable = false)
	private String documentNo;

	@Column(length = 50, nullable = false)
	private String driver;

	@Column(name="dept_name", length = 50, nullable = false)
	private String deptName;

	@Column(length = 50, nullable = false)
	private String car;

	@Column(length = 100, nullable = false)
	private String reason;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	@Column(name = "drv_start", nullable = false)
	private LocalDateTime drvStart;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	@Column(name = "drv_end", nullable = false)
	private LocalDateTime drvEnd;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	@Column(name = "drv_return")
	private LocalDateTime drvReturn;

	@Column(name = "before_mileage")
	private long beforeMileage;

	@Column(name = "after_mileage")
	private long afterMileage;


}
