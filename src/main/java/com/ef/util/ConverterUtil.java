package com.ef.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ConverterUtil {

	public static LocalDateTime convertDateToLocalDateTime(String date) {
		return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}
	
}
