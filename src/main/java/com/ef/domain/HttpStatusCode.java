package com.ef.domain;

public enum HttpStatusCode {
	
	INFORMATIONAL (100, "Informational"),
	
	SUCCESS (200, "Success"),
	
	REDIRECTION (300, "Redirection"),
	
	CLIENT_ERROR (400, "Client Error"),
	
	SERVER_ERROR (500, "Server Error");
	
	private final Integer value;
	private final String description;

	private HttpStatusCode(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
}
