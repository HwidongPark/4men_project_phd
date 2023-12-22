package com.itwill.fourmen.dto.post;

import java.time.LocalDate;

import com.itwill.fourmen.domain.Faq;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FaqListItemDto {
	private Long faq_id;
	private Long faq_num;
	private String faq_title;
	private String userid;
	private String faq_content;
	private LocalDate faq_created_time;
	private Long faq_view_count;
	
	// Post 모델(엔티티) 객체를 PostListItemDto 타입 객체로 변환해서 리턴.
	// static 이어야 함.
	public static FaqListItemDto fromEntity(Faq faq) {
		return FaqListItemDto.builder()
				.faq_id(faq.getFaq_id())
				.faq_num(faq.getFaq_num())
				.faq_title(faq.getFaq_title())
				.userid(faq.getUserid())
				.faq_content(faq.getFaq_content())
				.faq_created_time(faq.getFaq_created_time())
				.faq_view_count(faq.getFaq_view_count())
				.build();
	}
}
