package com.ef.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ef.dto.BlockCommentDTO;
import com.ef.model.BlockComment;
import com.ef.repository.BlockCommentRepository;

public class BlockCommentService {
	
	private BlockCommentRepository blockCommentRepository = new BlockCommentRepository();
	
	public List<BlockCommentDTO> saveBlockComments(List<BlockCommentDTO> list) {
		
		List<BlockComment> listBlockComment = blockCommentRepository.saveAll(list);
		
		final List<BlockCommentDTO> result = listBlockComment.stream().map(l -> mapToDTO(l)).collect(Collectors.toList());
		
		return result;
	}
	
	private BlockCommentDTO mapToDTO(BlockComment entity) {
		final BlockCommentDTO c = new BlockCommentDTO();

		c.setRequestDateTime(entity.getRequestDateTime());
		c.setIpAddress(entity.getIp());
		c.setStatus(entity.getStatus());
		c.setComment(entity.getComment());

		return c;
	}
	

}
