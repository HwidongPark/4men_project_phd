package com.itwill.fourmen.dto.post;

import java.time.LocalDate;

import com.itwill.fourmen.domain.Qna;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QnaLIstItemDto {
	private Long qna_id;
	private String qna_title;
	private String userid;
	private String qna_content;
	private LocalDate qna_created_time;
	private Long qna_view_count;
	
	// Qna 모델(엔티티) 객체를 QnaListItemDto 타입 객체로 변환해서 리턴.
	// static 이어야 함.
	public static QnaLIstItemDto fromEntity(Qna qna) {
		return QnaLIstItemDto.builder()
				.qna_id(qna.getQna_id())
				.qna_title(qna.getQna_title())
				.userid(qna.getUserid())
				.qna_content(qna.getQna_content())
				.qna_created_time(qna.getQna_created_time())
				.qna_view_count(qna.getQna_view_count())
				.build();
	}
}
