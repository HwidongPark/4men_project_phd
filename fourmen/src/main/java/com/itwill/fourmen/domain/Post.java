package com.itwill.fourmen.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

// DB 테이블 중 free_board 테이블. 필드 이름은 테이블 컬럼 이름과 같게.
@Data
@Builder
public class Post {
	private Long post_id;
	private Long post_num;
	private String post_title;
	private String userid;
	private String post_content;
	private LocalDate post_created_time;
	private LocalDateTime post_deleted_time;
	private Long post_view_count;
}