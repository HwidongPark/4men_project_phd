package com.itwill.fourmen.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.fourmen.dto.comment.CommentListDto;
import com.itwill.fourmen.dto.comment.CommentRegisterDto;
import com.itwill.fourmen.dto.comment.CommentUpdateDto;
import com.itwill.fourmen.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

	private final CommentService commentService;
	
	// 댓글 등록.
	@PostMapping
	public ResponseEntity<Integer> registerComment(@RequestBody CommentRegisterDto dto){
		log.debug("=======================================");
		log.debug("CommentRestController - REGISTERCOMMENT()");
		log.debug("REGISTERCOMMENT() - CommentRegisterDto = {}", dto);
		log.debug("=======================================");
		
		int result = commentService.registerComment(dto);
		
		return ResponseEntity.ok(result);
	}
	// 댓글 수정
	@PutMapping("/{comment_id}")
	public ResponseEntity<Integer> updateComment(@PathVariable long comment_id, @RequestBody CommentUpdateDto dto){
		log.debug("=======================================");
		log.debug("CommentRestController - updateComment()");
		log.debug("updateComment() - CommentUpdateDto = {}", dto);
		log.debug("=======================================");
		
		dto.setComment_id(comment_id);
		
		int result = commentService.updateComment(dto);
		
		return ResponseEntity.ok(result);
	}
	
	// 각 작품/게시판에 등록된 댓글을 가져옴.
	@GetMapping("/all/{worksid}")
	public ResponseEntity<List<CommentListDto>> getAllComments(@PathVariable long worksid){
		log.debug("=======================================");
		log.debug("CommentRestController - GetAllComment()");
		log.debug("GetAllComment() - WORKSID = {}", worksid);
		log.debug("=======================================");
		
		List<CommentListDto> commentList = commentService.getAllComments(worksid);
		
		return ResponseEntity.ok(commentList);
	}
	
	// 수정할 댓글을 모달창에 띄우기 위한 GET
	@GetMapping("/{comment_id}")
	public ResponseEntity<CommentListDto> getCommentByCommentId(@PathVariable long comment_id){
		log.debug("==============================");
		log.debug("CommentRestController - getCommentByCommentId()");
		log.debug("getCommentByCommentId - COMMENT_ID = {}", comment_id);
		log.debug("==============================");
		
		CommentListDto dto = commentService.getComment(comment_id);
		
		return ResponseEntity.ok(dto);
	}
	// 선택된 댓글 한가지만 삭제
	@DeleteMapping("/{comment_id}")
	public ResponseEntity<Integer> deleteComment(@PathVariable long comment_id){
		log.debug("=====================================");
		log.debug("CommentRestController - deleteComment()");
		log.debug("deleteComment - COMMENT_ID = {}", comment_id);
		log.debug("=====================================");
		
		int result = commentService.deleteComment(comment_id);
		
		return ResponseEntity.ok(result);
	}
	// 글이 삭제된다면 그 글에 종속된 댓글 모두 삭제
	@DeleteMapping("/all/{worksid}")
	public ResponseEntity<Integer> deleteAllComments(@PathVariable long worksid){
		log.debug("=====================================");
		log.debug("commentRestController - deleteAllComments()");
		log.debug("deleteAllComments - WORKSID = {}", worksid);
		log.debug("=====================================");
		
		int result = commentService.deleteAllComments(worksid);
		
		return ResponseEntity.ok(result);
	}
}
