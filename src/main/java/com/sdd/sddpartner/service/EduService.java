package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Edu;
import com.sdd.sddpartner.domain.SearchCategoryDate;

import java.util.List;

public interface EduService {

	void create(Edu edu) throws Exception;

	Edu select(Long eduNo) throws Exception;

	void update(Edu edu) throws Exception;

	void delete(Long eduNo) throws Exception;

	List<Edu> List() throws Exception;
	List<Edu> searchList(SearchCategoryDate search) throws Exception;

}
