package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;

import java.util.List;

public interface UseService {

	Ea read(Long documentNo) throws Exception;
	void modify(Ea ea) throws Exception;
    List<Ea> scheduleList(List<Long> categoryId) throws Exception;
    List<Ea> fourList() throws Exception;
	List<Ea> categoryList(List<Long> categoryId) throws Exception;
	List<Ea> searchList(List<Long> categoryId, List<Long> approve) throws Exception;
	//List<Ea> searchListTitle(List<Long> categoryId, List<Long> approve, String title) throws Exception;


}
