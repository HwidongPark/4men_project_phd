package com.itwill.fourmen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Comments;
import com.itwill.fourmen.dto.comment.CommentListDto;
import com.itwill.fourmen.dto.comment.CommentRegisterDto;
import com.itwill.fourmen.dto.comment.CommentUpdateDto;
import com.itwill.fourmen.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentDao commentDao;
	
	// WORKSID를 기반으로 댓글을 전부 가져오는 메서드
	public List<CommentListDto> getAllComments(long worksid){
		log.debug("======================================");
		log.debug("CommentService - read(WORKSID = {})", worksid);
		log.debug("======================================");
		
		List<Comments> commentList = commentDao.selectByWorksId(worksid);
		
		return commentList.stream().map(CommentListDto::fromEntity).toList();
	}// end of READ 
	
	public int registerComment(CommentRegisterDto dto) {
		log.debug("======================================");
		log.debug("CommentService - REGISTERCOMMENT()");
		log.debug("REGISTERCOMMENT - DTO = {}",dto);
		log.debug("======================================");
		
		int result = commentDao.registerComment(dto.toEntity());
		log.debug("======댓글 등록 결과======");
		log.debug(">> {}",result);
		log.debug("==========================");
		
		return result;
	}// end of RegisterComment
	
	public CommentListDto getComment(long comment_id) {
		log.debug("===========================");
		log.debug("CommentService - getComment()");
		log.debug("GetComment - COMMENT_ID = {}", comment_id);
		log.debug("===========================");
		
		Comments getComment = commentDao.getComment(comment_id);
		log.debug("============================");
		log.debug("getComment - {}", getComment.toString());
		log.debug("============================");
		
		return CommentListDto.fromEntity(getComment);
	}
	
	public int updateComment(CommentUpdateDto dto) {
		log.debug("===========================");
		log.debug("CommentService - UPDATECOMMENT()");
		log.debug("UPDATECOMMENT - DTO = {}", dto);
		log.debug("===========================");
		
		int result = commentDao.updateComment(dto.toEntity());
		log.debug("======댓글 수정 결과======");
		log.debug(">> {}",result);
		log.debug("==========================");
		
		return result;
	}// end of updateComment
	
	public int deleteComment(long comment_id) {
		log.debug("=========deleteComment==========");
		log.debug(">> COMMENT_ID = {}", comment_id);
		log.debug("================================");
		
		int result = commentDao.deleteCommentByCommentId(comment_id);
		log.debug("======댓글 삭제 결과======");
		log.debug(">> {}", result);
		log.debug("==========================");
		return result;
	}// end of deleteComment
	
}// end of CommentSerivce
