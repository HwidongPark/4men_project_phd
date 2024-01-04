package com.itwill.fourmen.dto.post;

import com.itwill.fourmen.domain.Notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeModifyDto {
	private String notice_title;
	private String notice_content;
	private Long notice_id;
	
	public Notice toEntity() {
		return Notice.builder()
				.notice_id(notice_id)
				.notice_title(notice_title)
				.notice_content(notice_content)
				.build();
	}
}