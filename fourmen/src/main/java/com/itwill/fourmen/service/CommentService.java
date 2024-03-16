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
		
		Long parent_num = dto.getParent_comment_id();
		log.debug("PARENTNUM = {}", dto.getParent_comment_id());
		
		if(parent_num != null) {
			// 답글이 달리는 부분
			
			// 부모 댓글 객체를 가져옴
			Comments parentComments = commentDao.getComment(dto.getParent_comment_id());
			log.debug("======부모 댓글 객체======");
			log.debug("PARENT COMMENT = {}", parentComments);
			// 부모 댓글의 누적된 댓글 수를 가져옴
			Long acc_answer_comment = parentComments.getAcc_comment();
			log.debug("======부모 댓글의 누적된 댓글 수======");
			log.debug("PARENT COMMENT -> ACC_ANSWER_COMMENT = {}", acc_answer_comment);
			// 부모 댓글의 그룹 번호를 새로이 등록될 댓글 DTO에 셋팅함 
			// -> 같은 그룹으로 묶는다
			dto.setC_group(parentComments.getC_group());
			log.debug("=====자식 댓글 그룹화=====");
			log.debug("CHILD COMMENT GROUP = {}", dto.getC_group());
			// 부모 댓글의 step을 가져오고 자식의 step을 1 추가 한다.
			// 여기가 문제인 듯 함... -> 고친듯?..
			
			// TODO: 계층 보다 레벨을 먼저 추가하고 계층을 추가 하는 방식이 나을 듯 함.
			if(parentComments.getC_group_level() == 0) {
				dto.setC_group_level(acc_answer_comment+1L);
			} else {
				dto.setC_group_level(parentComments.getC_group_level());
			}
			
			Long newCommentLevel = dto.getC_group_level();
			log.debug("====새로운 댓글 레벨 설정====");
			log.debug("CHILD COMMENT LEVEL = {}", newCommentLevel);

			log.debug("=====그룹 내의 같은 레벨의 최대 계층 값 가져오기=======");
			Long maxGroupDepth = commentDao.getMaxGroupDepth(parentComments.getC_group_level());
			
			dto.setC_group_depth(maxGroupDepth + 1L); 
			
			Long childDepth = dto.getC_group_depth();
			log.debug("CHILD COMMENT DEPTH = {}", childDepth);
			
			dto.setAcc_comment(0L);
			log.debug("====================");
			log.debug("댓글 등록 직전 마지막 DTO 점검 = {}", dto);
			log.debug("====================");
			
			// 댓글 등록
			int result = commentDao.registerComment(dto.toEntity());
						
			commentDao.increaseAccAnswer(dto.getParent_comment_id());
			
			Comments updateParentComment = commentDao.getComment(dto.getParent_comment_id());
			log.debug("부모 댓글에 새로운 자식 댓글이 추가 되었음을 표기 하기 위한 누적 댓글 추가");
			log.debug("PARENT_COMMENT_ID = {} , ANSWER NUM = {}", dto.getParent_comment_id(), updateParentComment.getAcc_comment());
			
			return result;
		} else  {
			// 	답글이 아닐때 즉 새로운 댓글이 달릴 때
			// REF 즉 그룹이 아예 없다면
			Long maxGroup = commentDao.getMaxGroupNum(dto.getWorksid());
			
			log.debug("MAXGROUP = {}", maxGroup);
			
			if(maxGroup == null) {
			// 첫 댓글 그룹이므로 0으로 세팅한다	
				dto.setC_group(0L);
				log.debug("SET C GROUP = {}", dto.getC_group());
			} else {
			// 그게 아니라면 기존 댓글 그룹의 최대 그룹 번호를 가져와서 1 추가한다.
				dto.setC_group(maxGroup + 1L);
				log.debug("SET C GROUP = {}", dto.getC_group());
			}
			// 새로이 등록되는 댓글 즉 부모 댓글이기에 초기 그룹 레벨과 스탭
			// 부모 번호, 누적 댓글 수를 모두 0으로 세팅한다.
			dto.setC_group_level(0L);
			dto.setC_group_depth(0L);
			dto.setParent_comment_id(0L);
			dto.setAcc_comment(0L);
			log.debug("====================");
			log.debug("FINAL DTO = {}", dto);
			log.debug("====================");
			
			int result = commentDao.registerComment(dto.toEntity());
			log.debug("======댓글 등록 결과======");
			log.debug(">> {}",result);
			log.debug("==========================");
			
			return result;
		}
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
		
		Comments deletedComments = commentDao.getComment(comment_id);

		// 삭제하려는 코멘트가 최상위 코멘트일 경우
		if(deletedComments.getAcc_comment() != 0) {
			int result = commentDao.updateParentCommentContentSetNull(comment_id);
			return result;
		} else {
			int result = commentDao.deleteCommentByCommentId(comment_id);
			return result;
		}
	}// end of deleteComment
	
	
	public int deleteAllComments(long worksid) {
		log.debug("==========deleteAllComment=========");
		log.debug(">> WORKSID = {}", worksid);
		log.debug("===================================");
		
		int result = commentDao.deleteAllCommentsByWorksId(worksid);
		
		log.debug("====댓글 삭제 결과====");
		log.debug(">> result {}", result);
		log.debug("======================");
		
		return result;
	}
	
}// end of CommentSerivce
