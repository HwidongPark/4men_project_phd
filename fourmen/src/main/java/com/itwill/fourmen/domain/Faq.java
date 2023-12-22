package com.itwill.fourmen.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

// DB 테이블 중 faq_board 테이블. 필드 이름은 테이블 컬럼 이름과 같게.
@Data
@Builder
public class Faq {
	private Long faq_id;
	private Long faq_num;
	private String faq_title;
	private String userid;
	private String faq_content;
	private LocalDate faq_created_time;
	private LocalDateTime faq_deleted_time;
	private Long faq_view_count;
}
