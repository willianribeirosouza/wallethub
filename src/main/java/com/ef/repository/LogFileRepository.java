package com.ef.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ef.dto.LogFileDTO;
import com.ef.dto.LogFileFilterDTO;
import com.ef.model.LogFile;
import com.ef.util.HibernateUtil;

public class LogFileRepository {
	
	public List<LogFile> list(LogFileFilterDTO filter) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			
			@SuppressWarnings("unchecked")
			Query<LogFile> query = session.createQuery("select l from LogFile l where l.requestDateTime >= :startDate and l.requestDateTime <= :endDate group by l.ip having count(l.ip) > :threshold");
			query.setParameter("startDate", filter.getStartDate());
			query.setParameter("endDate", getEndDate(filter.getStartDate(), filter.getDuration()));
			query.setParameter("threshold", filter.getThreshold().longValue());
			
			List<LogFile> list = query.list();
			
			transaction.commit();
			
			return list;
		} catch (Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		
		
		return null;
		
	}

	private LocalDateTime getEndDate(LocalDateTime startDate, String duration) {
		if("hourly".equals(duration)) {
			return startDate.plusHours(1);
			
		} else if("daily".equals(duration)) {
			return startDate.plusDays(1);
		}
		 return null;
	}

	public List<LogFile> saveAll(List<LogFileDTO> list) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<LogFile> result = new ArrayList<LogFile>();

		try {

			transaction = session.beginTransaction();
			System.out.println("Importing file...(" + now() + ")");

			list.stream().forEach(log -> {
				LogFile logFile = this.mapToEntity(log);
				session.save(logFile);
				result.add(logFile);
			});

			transaction.commit();
			System.out.println("Done! (" + now() + ")");

		} catch (Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	private LogFile mapToEntity(LogFileDTO log) {
		final LogFile l = new LogFile();

		l.setRequestDateTime(log.getRequestDateTime());
		l.setIp(log.getIpAddress());
		l.setRequest(log.getRequest());
		l.setStatus(log.getStatus());
		l.setUserAgent(log.getUserAgent());

		return l;
	}

	public String now() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
