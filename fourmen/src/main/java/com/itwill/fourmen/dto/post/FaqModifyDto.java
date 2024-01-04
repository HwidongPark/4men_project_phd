package com.itwill.fourmen.dto.post;

import com.itwill.fourmen.domain.Faq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqModifyDto {
	private String faq_title;
	private String faq_content;
	private Long faq_id;
	
	public Faq toEntity() {
		return Faq.builder()
				.faq_id(faq_id)
				.faq_title(faq_title)
				.faq_content(faq_content)
				.build();
	}
}