package com.ef.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ef.dto.LogFileDTO;
import com.ef.dto.LogFileFilterDTO;
import com.ef.model.LogFile;
import com.ef.repository.LogFileRepository;

public class LogFileService {
	
	private LogFileRepository logFileRepository = new LogFileRepository();
	
	public List<LogFileDTO> listIpsByCriteria(LogFileFilterDTO filter) {

		final List<LogFile> listLogFile = logFileRepository.list(filter);
		
		final List<LogFileDTO> result = listLogFile.stream().map(l -> mapToDTO(l)).collect(Collectors.toList());
		return result;
	}
	
	private LogFileDTO mapToDTO(LogFile log) {
		final LogFileDTO l = new LogFileDTO();

		l.setId(log.getId());
		l.setRequestDateTime(log.getRequestDateTime());
		l.setIpAddress(log.getIp());
		l.setRequest(log.getRequest());
		l.setStatus(log.getStatus());
		l.setUserAgent(log.getUserAgent());

		return l;
	}
}