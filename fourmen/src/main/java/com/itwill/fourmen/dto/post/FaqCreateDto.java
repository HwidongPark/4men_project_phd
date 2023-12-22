package com.itwill.fourmen.dto.post;

import com.itwill.fourmen.domain.Faq;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FaqCreateDto {
	private String faq_title;
	private String faq_content;
	private String user_id;
	
	// PostCreateDTO 객체를 Faq 모델(엔티티) 객체로 변환해서 리턴하는 메서드.
	// static일 필요가 없음. 사용자가 입력한 데이터의 타입이 Faq이므로
	// 이를 DTO로 변환하는 과정이 필요함.
	public Faq toEntity() {
		return Faq.builder()
				.faq_title(faq_title)
				.faq_content(faq_content)
				.userid(user_id)
				.build();
	}
}
