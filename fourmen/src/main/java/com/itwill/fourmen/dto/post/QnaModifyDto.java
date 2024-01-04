package com.itwill.fourmen.dto.post;

import com.itwill.fourmen.domain.Qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnaModifyDto {
	private String qna_title;
	private String qna_content;
	private Long qna_id;
	
	public Qna toEntity() {
		return Qna.builder()
				.qna_id(qna_id)
				.qna_title(qna_title)
				.qna_content(qna_content)
				.build();
	}
}