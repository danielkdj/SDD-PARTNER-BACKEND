package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Edu;
import com.sdd.sddpartner.domain.SearchCategoryDate;

import java.util.List;

public interface EduService {

	public void create(Edu edu) throws Exception;

	public Edu select(Long eduNo) throws Exception;

	public void update(Edu edu) throws Exception;

	public void delete(Long eduNo) throws Exception;

	public List<Edu> List() throws Exception;
	public List<Edu> searchList(SearchCategoryDate search) throws Exception;

}
