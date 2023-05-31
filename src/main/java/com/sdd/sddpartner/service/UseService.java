package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;

import java.util.List;

public interface UseService {

	Ea read(Long documentNo) throws Exception;
    List<Ea> fourList() throws Exception;
	List<Ea> categoryList(List<Long> categoryId) throws Exception;
    List<Ea> scheduleList(List<Long> categoryId) throws Exception;
	List<Ea> searchList(List<Long> categoryId, List<Long> approve) throws Exception;
	void modify(Long documentNo, Long approve) throws Exception;
	void modifyAndCreate(Long documentNo, Long approve) throws Exception;
    Long mainCount(List<Long> categoryIdList);
	Ea mainDate(List<Long> categoryIdList);
}
