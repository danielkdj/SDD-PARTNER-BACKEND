package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Completion;
//import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ComRepository extends JpaRepository<Completion, Long> {
    List<Completion> findAll(Sort comNo);
    List<Completion> findByEduInfo_EduIdInAndCompletionInAndEmployee_Dept_DeptNoInAndYearsAndQuartersIn(Sort comNo, List<Long> eduIds, List<Character> completions, List<Long> deptNos, Long years, List<Long> quarters);
    Long countByEduInfo_EduIdInAndCompletionInAndYearsAndQuartersIn(Sort comNo, List<Long> eduIds, List<Character> completions, Long years, List<Long> quarters);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Completion (com_no, emp_id, edu_id, years, quarters, completion) " +
            "(SELECT NULL, e.emp_id, :eduId, :years, :quarters, 'N' " +
            "FROM Employee e " +
            "WHERE NOT EXISTS (SELECT base FROM Completion c WHERE c.emp_id = e.emp_id AND c.edu_id = e.edu_id))", nativeQuery = true)
    Integer insertCompletionFromEmployee(Long eduId, Long years, Long quarters);
}
