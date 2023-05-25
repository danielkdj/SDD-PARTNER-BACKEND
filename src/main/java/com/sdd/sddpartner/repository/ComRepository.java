package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Completion;
//import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComRepository extends JpaRepository<Completion, Long> {


//    List<Completion> findByEduInfo_EduIdContainingAndYearsContainingAndQuartersContainingAndCompletionContaining(Sort sort, Long eduId, Long years, Long quarters, Character completion);
//    List<Completion> findByCompletionContaining(Sort sort, Character completion);


}
