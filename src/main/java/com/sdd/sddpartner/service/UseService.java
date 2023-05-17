package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.domain.SearchCategoryDate;

import java.util.List;

public interface UseService {

	Ea select(Long documentNo) throws Exception;

	void update(Ea ea) throws Exception;

	List<Ea> categoryList(long category) throws Exception;
	List<Ea> searchList(SearchCategoryDate search) throws Exception;

}
