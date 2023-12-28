package com.itwill.fourmen.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

//DB 테이블 중 qna_board 테이블. 필드 이름은 테이블 컬럼 이름과 같게.
@Data
@Builder
public class Qna {
	private Long qna_id;
	private String qna_title;
	private String userid;
	private String qna_content;
	private LocalDate qna_created_time;
	private LocalDateTime qna_deleted_time;
	private Long qna_view_count;
}
