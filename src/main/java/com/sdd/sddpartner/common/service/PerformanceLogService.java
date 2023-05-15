package com.sdd.sddpartner.common.service;

import com.sdd.sddpartner.common.domain.PerformanceLog;

import java.util.List;



public interface PerformanceLogService {

	public void register(PerformanceLog performanceLog) throws Exception;
	
	public List<PerformanceLog> list() throws Exception;

}
