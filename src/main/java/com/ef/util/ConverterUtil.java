package com.ef.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ConverterUtil {

	public static LocalDateTime convertDateToLocalDateTime(String date) {
		return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}

	public static String returnComment(Integer status) {
		String comment = "";

		switch (status) {
		case 400:
			comment = "400 Bad Request";
			break;
		case 401:
			comment = "401 Unauthorized";
			break;
		case 402:
			comment = "402 Payment Required";
			break;
		case 403:
			comment = "403 Forbidden";
			break;
		case 404:
			comment = "404 Not Found";
			break;
		case 405:
			comment = "405 Method Not Allowed";
			break;
		case 406:
			comment = "406 Not Acceptable";
			break;
		case 407:
			comment = "407 Proxy Authentication Required";
		case 408:
			comment = "408 Request Timeout";
			break;
		case 409:
			comment = "409 Conflict";
			break;
		case 410:
			comment = "410 Gone";
			break;
		case 411:
			comment = "411 Length Required";
			break;
		case 412:
			comment = "412 Precondition Failed";
			break;
		case 413:
			comment = "413 Request Entity Too Large";
			break;
		case 414:
			comment = "414 Request-URI Too Long";
			break;
		case 415:
			comment = "415 Unsupported Media Type";
			break;
		case 416:
			comment = "416 Requested Range Not Satisfiable";
			break;
		case 417:
			comment = "417 Expectation Failed";
			break;
		case 418:
			comment = "418 I'm a teapot (RFC 2324)";
			break;
		case 420:
			comment = "420 Enhance Your Calm (Twitter)";
			break;
		case 422:
			comment = "422 Unprocessable Entity (WebDAV)";
			break;
		case 423:
			comment = "423 Locked (WebDAV)";
			break;
		case 424:
			comment = "424 Failed Dependency (WebDAV)";
			break;
		case 425:
			comment = "425 Reserved for WebDAV";
			break;
		case 426:
			comment = "426 Upgrade Required";
			break;
		case 428:
			comment = "428 Precondition Required";
			break;
		case 429:
			comment = "429 Too Many Requests";
			break;
		case 431:
			comment = "431 Request Header Fields Too Large";
			break;
		case 444:
			comment = "444 No Response (Nginx)";
			break;
		case 449:
			comment = "449 Retry With (Microsoft)";
			break;
		case 450:
			comment = "450 Blocked by Windows Parental Controls (Microsoft)";
			break;
		case 451:
			comment = "451 Unavailable For Legal Reasons";
			break;
		case 499:
			comment = "499 Client Closed Request (Nginx)";
		default:
			comment = "Not Blocked";
		}

		return comment;
	}

}
