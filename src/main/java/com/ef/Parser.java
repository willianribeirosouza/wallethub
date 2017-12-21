package com.ef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ef.dto.BlockCommentDTO;
import com.ef.dto.LogFileDTO;
import com.ef.dto.LogFileFilterDTO;
import com.ef.loader.LogFileLoader;
import com.ef.service.BlockCommentService;
import com.ef.service.LogFileService;
import com.ef.util.ConverterUtil;

public class Parser {

	public static void main(String[] args) {

		try {

			//args = setParameters(args);
			
			
			if (args.length > 0) {

				String duration = getArguments(args).get("duration");
				String startDate = getArguments(args).get("startDate");
				String accesslog = getArguments(args).get("accesslog");
				Integer threshold = getArguments(args).get("threshold") != null ? Integer.parseInt(getArguments(args).get("threshold")) : null;

				if (invalidInputs(duration, startDate, threshold)) {
					showHowItWorks();

				} else {
					// Check if the path of the file parameter is present to load data
					if (accesslog != null && !accesslog.isEmpty()) {
						// Load Information to DB
						LogFileLoader.load(accesslog);
					}

					final LogFileFilterDTO filter = new LogFileFilterDTO();
					filter.setDuration(duration);
					filter.setStartDate(ConverterUtil.convertDateToLocalDateTime2(startDate));
					filter.setThreshold(threshold);

					final LogFileService service = new LogFileService();

					final List<LogFileDTO> listIp = service.listIpsByCriteria(filter);

					System.err.println();
					System.err.println();
					System.err.println("-------------------------------------------------------------------------------------------------------------------------------");
					System.err.println(listIp.size() + " rows was found.");
					System.err.println("-------------------------------------------------------------------------------------------------------------------------------");
					System.err.println();
					System.err.println();
					
					if(!listIp.isEmpty()) {
						
						List<BlockCommentDTO> listBC = new ArrayList<BlockCommentDTO>();
						
						for(LogFileDTO dto : listIp) {
							System.out.println("The IP: " + dto.getIpAddress() + " has made more then " + filter.getThreshold() + " requests.");
							
							final BlockCommentDTO bc = new BlockCommentDTO();
							bc.setComment(ConverterUtil.returnComment(dto.getStatus()));
							bc.setIpAddress(dto.getIpAddress());
							bc.setRequestDateTime(dto.getRequestDateTime());
							bc.setStatus(dto.getStatus());
							
							listBC.add(bc);
						}
						
						System.err.println("-------------------------------------------------------------------------------------------------------------------------------");
						System.err.println();
						System.err.println();
						
						if(!listBC.isEmpty()) {
							final BlockCommentService blockCommentService = new BlockCommentService();
							blockCommentService.saveBlockComments(listBC);
						}
					}
				}

			} else {
				showHowItWorks();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	private static void showHowItWorks() {
		System.err.println("");
		System.err.println("");
		System.err.println("-------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(">>>>>>>>>>This is how the tool works:<<<<<<<<<<");
		System.err.println("");
		System.out.println("Example 1: java -cp \"parser.jar\" com.ef.Parser --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100");
		System.out.println("The tool will find any IPs that made more than 100 requests starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00 (one hour)");
		System.out.println("and print them to console AND also load them to another MySQL table with comments on why it's blocked.");
		System.err.println("-------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Example 2: java -cp \"parser.jar\" com.ef.Parser --startDate=2017-01-01.13:00:00 --duration=daily --threshold=250");
		System.out.println("The tool will find any IPs that made more than 250 requests starting from 2017-01-01.13:00:00 to 2017-01-02.13:00:00 (24 hours)");
		System.out.println("and print them to console AND also load them to another MySQL table with comments on why it's blocked.");
		System.err.println("-------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Example 3: java -cp \"parser.jar\" com.ef.Parser --accesslog=/path/to/file --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100");
		System.out.println("The tool will FIRST IMPORT the file from the path on acesslog parameter and then find any IPs that made more than 100 requests starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00 (one hour)");
		System.out.println("and print them to console AND also load them to another MySQL table with comments on why it's blocked.");
		System.err.println("-------------------------------------------------------------------------------------------------------------------------------");
		System.err.println("");
		System.err.println("");
	}

	@SuppressWarnings("unused")
	private static String[] setParameters(String[] args) {
		args = new String[] { 
				"--accesslog=/Users/willian/Desktop/Java_MySQL_Test/access.log", 
				"--startDate=2017-01-01.00:00:00", 
				"--duration=hourly", 
				"--threshold=1" };

		return args;
	}

	private static boolean invalidInputs(final String duration, final String startDate, final Integer threshold) {
		if (duration == null || duration.isEmpty()) {
			return true;
		}

		if (startDate == null || startDate.isEmpty()) {
			return true;
		}

		if (threshold == null) {
			return true;
		}
		return false;

	}

	public static HashMap<String, String> getArguments(String[] array) {
		return (HashMap<String, String>) Stream.of(array).map(elem -> elem.split("\\=")).filter(elem -> elem.length == 2).collect(Collectors.toMap(e -> e[0], e -> e[1])).entrySet().stream().collect(Collectors.toMap(e -> e.getKey().split("\\-\\-")[1], e -> e.getValue()));
	}

}
