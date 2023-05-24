package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Completion;
import com.sdd.sddpartner.domain.Department;
import com.sdd.sddpartner.domain.Notice;

import java.util.List;

public interface ComService {
	Completion read(Long ComNo) throws Exception;

	List<Completion> list() throws Exception;
	//List<Completion> list(Completion search) throws Exception;

	//void modifyAll(List<Long> comNo) throws Exception;
	//Completion modify(Long comNo) throws Exception;
	void modify(Long comNo) throws Exception;


}
