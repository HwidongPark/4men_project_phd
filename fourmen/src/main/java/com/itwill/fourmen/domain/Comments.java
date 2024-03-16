package com.itwill.fourmen.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comments {

	private Long comment_id; // 댓글 아이디 Sequence
	private Long c_group; // 그룹화
	private Long c_group_level; // 그룹 안의 레벨
	private Long worksid; // works 테이블의 id를 참조할 예정 추가로 DB 통합시 게시판 아이디까지 할 수 있도록
	private Long c_group_depth; // 그룹 안의 깊이
	private Long parent_comment_id; // 부모의 댓글 아이디
	private Long acc_comment; // 달린 답글의 수
	private String comment_writer; // 로그인한 사용자의 아이디가 들어갈 예정
	private String comment_content; // 작성 댓글 
	private LocalDateTime created_time; // 작성 시간 
	private LocalDateTime modified_time; // 수정 시간
	
}
