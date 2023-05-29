package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Completion;
import java.util.List;

public interface ComService {
	//Completion read(Long ComNo) throws Exception;

	List<Completion> list() throws Exception;
	List<Completion> searchList(List<Long> eduIds, List<Character> completions, List<Long> deptNos, Long years, List<Long> quarters) throws Exception;
	Long count(List<Long> eduIds, List<Character> completions, Long year, List<Long> quarters);
	void modify(Long comNo) throws Exception;
	List<Completion> register(Long eduId, Long years, Long quarters);

}
