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
	private Long worksid; // works 테이블의 id를 참조할 예정 추가로 DB 통합시 게시판 아이디까지 할 수 있도록
	private String comment_writer; // 로그인한 사용자의 아이디가 들어갈 예정
	private String comment_content;
	private String post_category; // 게시판의 이름을 적을 예정.
	private LocalDateTime created_time;
	private LocalDateTime modified_time;
	
}
