package com.itwill.fourmen.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

// DB 테이블 중 notice_board 테이블. 필드 이름은 테이블 컬럼 이름과 같게.
@Data
@Builder
public class Notice {
	private Long notice_id;
	private Long notice_num;
	private String notice_title;
	private String userid;
	private String notice_content;
	private LocalDate notice_created_time;
	private LocalDateTime notice_deleted_time;
	private Long notice_view_count;
}
