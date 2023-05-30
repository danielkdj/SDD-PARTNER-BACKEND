package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.dto.EaDto;
import com.sdd.sddpartner.domain.Ea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EaRepository extends JpaRepository<Ea, Long> {
    @Query( "SELECT e.documentNo, e.employee.empId, emp.name, e.categoryItem.categoryId, c.category, c.subCategory, e.title, e.content, e.startDate,e.endDate, e.approvalStatus, e.createdAt " +
            "FROM Ea e " +
            "LEFT JOIN CategoryItem c ON (e.categoryItem.categoryId = c.categoryId) " +
            "LEFT JOIN Employee emp ON (e.employee.empId = emp.empId) " +
            "WHERE e.approvalStatus = 1 " +
            "ORDER BY e.documentNo DESC ")
    public List<Object[]> findEaApproval();

    @Query( "SELECT e.documentNo, e.employee.empId, emp.name, e.categoryItem.categoryId, c.category, c.subCategory, e.title, e.content, e.startDate,e.endDate, e.approvalStatus, e.createdAt " +
            "FROM Ea e " +
            "LEFT JOIN CategoryItem c ON (e.categoryItem.categoryId = c.categoryId) " +
            "LEFT JOIN Employee emp ON (e.employee.empId = emp.empId) " +
            "WHERE e.approvalStatus = 1 " +
            "AND e.categoryItem.categoryId = 1 " +
            "ORDER BY e.documentNo DESC ")
    public List<Object[]> findEaAttendance();

    @Query( "SELECT e.documentNo, e.employee.empId, emp.name, e.categoryItem.categoryId, c.category, c.subCategory, e.title, e.content, e.startDate,e.endDate, e.approvalStatus, e.createdAt " +
            "FROM Ea e " +
            "LEFT JOIN CategoryItem c ON (e.categoryItem.categoryId = c.categoryId) " +
            "LEFT JOIN Employee emp ON (e.employee.empId = emp.empId) " +
            "WHERE e.approvalStatus = 1 " +
            "AND e.categoryItem.categoryId < 10 " +
            "AND e.categoryItem.categoryId > 1 " +
            "ORDER BY e.documentNo DESC ")
    public List<Object[]> findEaVacation();

    @Query( "SELECT e.documentNo, e.employee.empId, emp.name, e.categoryItem.categoryId, c.category, c.subCategory, e.title, e.content, e.startDate,e.endDate, e.approvalStatus, e.createdAt " +
            "FROM Ea e " +
            "LEFT JOIN CategoryItem c ON (e.categoryItem.categoryId = c.categoryId) " +
            "LEFT JOIN Employee emp ON (e.employee.empId = emp.empId) " +
            "WHERE e.approvalStatus = 1 " +
            "AND e.categoryItem.categoryId > 10 " +
            "AND e.categoryItem.categoryId < 15 " +
            "ORDER BY e.documentNo DESC ")
    public List<Object[]> findEaAffair();

    @Query( "SELECT e.documentNo, e.employee.empId, emp.name, e.categoryItem.categoryId, c.category, c.subCategory, e.title, e.content, e.startDate,e.endDate, e.approvalStatus, e.createdAt " +
            "FROM Ea e " +
            "LEFT JOIN CategoryItem c ON (e.categoryItem.categoryId = c.categoryId) " +
            "LEFT JOIN Employee emp ON (e.employee.empId = emp.empId) " +
            "WHERE e.approvalStatus = 1 " +
            "AND e.categoryItem.categoryId > 14 " +
            "ORDER BY e.documentNo DESC ")
    public List<Object[]> findEaHR();


//    @Query(value = "SELECT e.documentNo, e.title, e.content, e.startDate, e.endDate, e.createdAt, e.approvalStatus, c.categoryId, c.category,c.subCategory, emp.empId, emp.name, emp.dept.deptNo, emp.dept.deptName " +
//            "FROM Ea e " +
//            "LEFT JOIN CategoryItem c ON (e.categoryItem.categoryId = c.categoryId) " +
//            "LEFT JOIN Employee emp ON (e.employee.empId = emp.empId) " +
//            "WHERE e.documentNo = :documentNo ")
//    public Ea getEaOne(Long documentNo);
}