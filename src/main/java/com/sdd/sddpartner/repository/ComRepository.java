package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Completion;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComRepository extends JpaRepository<Completion, Long> {
    List<Completion> findAll(Sort comNo);
    List<Completion> findByEduInfo_EduIdInAndCompletionInAndEmployee_Dept_DeptNoInAndYearsAndQuartersIn(Sort comNo, List<Long> eduIds, List<Character> completions, List<Long> deptNos, Long years, List<Long> quarters);
    Long countByEduInfo_EduIdInAndCompletionInAndYearsAndQuartersIn(Sort comNo, List<Long> eduIds, List<Character> completions, Long years, List<Long> quarters);

    boolean existsByYearsAndQuartersAndEmployee_EmpIdAndEduInfo_EduId(Long years, Long quarters, String empId, Long eduId);


}
