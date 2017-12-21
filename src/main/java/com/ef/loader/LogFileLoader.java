package com.ef.loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.ef.dto.LogFileDTO;
import com.ef.parser.LogFileParser;
import com.ef.repository.LogFileRepository;

public abstract class LogFileLoader {
	
	//TODO - Finish dependency injection configuration for repositoty classes
	//@Inject private static LogFileRepository logFileRepository;
	
	public static void load(String filename) {
		try {
			
			List<LogFileDTO> logFile = Files.lines(Paths.get(filename)).map(LogFileParser.mapLineToLogFileDTO).collect(Collectors.toList());
			
			final LogFileRepository logFileRepository = new LogFileRepository();
			logFileRepository.saveAll(logFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
