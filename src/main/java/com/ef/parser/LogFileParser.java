package com.ef.parser;

import java.util.List;
import java.util.function.Function;

import com.ef.dto.LogFileDTO;
import com.ef.util.ConverterUtil;
import com.google.common.base.Splitter;

public class LogFileParser {

	public static Function<String, LogFileDTO> mapLineToLogFileDTO = new Function<String, LogFileDTO>() {

		public LogFileDTO apply(String line) {

			LogFileDTO dto = new LogFileDTO();

			List<String> logPieces = Splitter.on("|").trimResults().omitEmptyStrings().splitToList(line);

			dto.setRequestDateTime(ConverterUtil.convertDateToLocalDateTime(logPieces.get(0)));
			dto.setIpAddress(logPieces.get(1));
			dto.setRequest(logPieces.get(2));
			dto.setStatus(Integer.parseInt(logPieces.get(3)));
			dto.setUserAgent(logPieces.get(4));
			return dto;
		}
	};
}