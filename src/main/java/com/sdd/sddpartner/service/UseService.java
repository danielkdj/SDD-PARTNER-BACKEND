package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;

import java.util.List;

public interface UseService {

	Ea read(Long documentNo) throws Exception;

	void modify(Ea notice) throws Exception;

	//List<Ea> uselist() throws Exception;

    List<Ea> schedulelist(Long categoryId) throws Exception;

    List<Ea> fourlist() throws Exception;
	List<Ea> categorylist(Long category) throws Exception;

	List<Ea> searchlist(String title) throws Exception;

}
