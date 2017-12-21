package com.ef.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ef.dto.BlockCommentDTO;
import com.ef.model.BlockComment;
import com.ef.util.HibernateUtil;

public class BlockCommentRepository {
	
	public List<BlockComment> saveAll(List<BlockCommentDTO> list) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<BlockComment> result = new ArrayList<BlockComment>();

		try {

			transaction = session.beginTransaction();
			System.out.println("Saving Comments...(" + now() + ")");

			list.stream().forEach(log -> {
				BlockComment bc = this.mapToEntity(log);
				session.save(bc);
				result.add(bc);
			});

			transaction.commit();
			System.out.println("Comments Saved! (" + now() + ")");

		} catch (Exception ex) {
			transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}
	
	private BlockComment mapToEntity(BlockCommentDTO dto) {
		final BlockComment c = new BlockComment();

		c.setRequestDateTime(dto.getRequestDateTime());
		c.setIp(dto.getIpAddress());
		c.setStatus(dto.getStatus());
		c.setComment(dto.getComment());

		return c;
	}
	
	public String now() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

}
