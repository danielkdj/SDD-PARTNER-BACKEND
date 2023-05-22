package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;

import java.util.List;

public interface UseService {

	public Ea read(Long documentNo) throws Exception;

	public void modify(Ea notice) throws Exception;

	public List<Ea> uselist() throws Exception;

    List<Ea> schedulelist(Long categoryId) throws Exception;

    public List<Ea> fourlist() throws Exception;
	public List<Ea> categorylist(Long category) throws Exception;

	public List<Ea> searchlist(String title) throws Exception;

}
