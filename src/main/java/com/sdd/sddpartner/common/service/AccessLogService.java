package com.sdd.sddpartner.common.service;

import java.util.List;

import com.sdd.sddpartner.common.domain.AccessLog;

public interface AccessLogService {

	public void register(AccessLog accessLog) throws Exception;

	public List<AccessLog> list() throws Exception;

}
