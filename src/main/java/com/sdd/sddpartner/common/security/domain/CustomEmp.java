//package com.sdd.sddpartner.common.security.domain;
//
//import com.sdd.sddpartner.domain.Employee;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//public class CustomEmp extends User {
//
//	private static final long serialVersionUID = 1L;
//
//	private Employee emp;
//
//	public CustomEmp(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, authorities);
//	}
//
////	public CustomEmp(Employee emp) {
////		super(emp.getEmpId(), emp.getPassword(), emp.getAuthList().stream()
////				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
////
////		this.emp = emp;
////	}
//
//	public CustomEmp(Employee Employee, Collection<? extends GrantedAuthority> authorities) {
//		super(Employee.getEmpId(), Employee.getPassword(), authorities);
//
//		this.emp = emp;
//	}
//
//	public String getEmpId() {
//		return emp.getEmpId();
//	}
//
//	public String getEmpSSN() {
//		return emp.getEmpSSN();
//	}
//
//}
