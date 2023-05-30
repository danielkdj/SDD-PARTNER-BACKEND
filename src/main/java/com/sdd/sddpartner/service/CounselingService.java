package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Counseling;
import com.sdd.sddpartner.dto.CounselingDto;


import java.util.List;

public interface CounselingService {
	Counseling save(Counseling coun) throws Exception;

	Counseling read(Long counId) throws Exception;

	List<Counseling> findByTitle(String title) throws Exception;

	Counseling update(Long counId, Counseling coun) throws Exception;

	List<CounselingDto> list() throws Exception;

	void delete(Long counId) throws Exception;
}