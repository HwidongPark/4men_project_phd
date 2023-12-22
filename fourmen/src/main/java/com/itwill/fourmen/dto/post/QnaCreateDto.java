package com.itwill.fourmen.dto.post;

import com.itwill.fourmen.domain.Qna;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QnaCreateDto {
	private String qna_title;
	private String qna_content;
	private String user_id;
	
	// PostCreateDTO 객체를 Qna 모델(엔티티) 객체로 변환해서 리턴하는 메서드.
	// static일 필요가 없음. 사용자가 입력한 데이터의 타입이 Qna이므로
	// 이를 DTO로 변환하는 과정이 필요함.
	public Qna toEntity() {
		return Qna.builder()
				.qna_title(qna_title)
				.qna_content(qna_content)
				.userid(user_id)
				.build();
	}
}
