package com.sdd.sddpartner.service;

import java.util.List;

import com.sdd.sddpartner.domain.Pds;

public interface PdsService {

	public void register(Pds item) throws Exception;

	public Pds read(Long itemId) throws Exception;

	public void modify(Pds item) throws Exception;

	public void remove(Long itemId) throws Exception;

	public List<Pds> list() throws Exception;

	public List<String> getAttach(Long itemId) throws Exception;

	public void updateAttachDownCnt(String fullName) throws Exception;
	
}
