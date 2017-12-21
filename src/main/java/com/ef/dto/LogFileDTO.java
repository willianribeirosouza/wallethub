package com.ef.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.dozer.Mapping;

public class LogFileDTO {
	
	@NotNull @Mapping("id")
	private int id;  
	
	@NotNull @Mapping("requestDateTime")
	private LocalDateTime requestDateTime;
	
	@NotNull @Mapping("ipAddress")
	private String ipAddress;
	
	@NotNull @Mapping("request")
	private String request;
	
	@NotNull @Mapping("status")
	private Integer status;
	
	@NotNull @Mapping("userAgent")
	private String userAgent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(LocalDateTime requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}