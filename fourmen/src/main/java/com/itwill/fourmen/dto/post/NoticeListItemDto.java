package com.itwill.fourmen.dto.post;

import java.time.LocalDate;

import com.itwill.fourmen.domain.Notice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeListItemDto {
	private Long notice_id;
	private String notice_title;
	private String userid;
	private String notice_content;
	private LocalDate notice_created_time;
	private Long notice_view_count;
	
	// Notice 모델(엔티티) 객체를 NoticeListItemDto 타입 객체로 변환해서 리턴.
	// static 이어야 함.
	public static NoticeListItemDto fromEntity(Notice notice) {
		return NoticeListItemDto.builder()
				.notice_id(notice.getNotice_id())
				.notice_title(notice.getNotice_title())
				.userid(notice.getUserid())
				.notice_content(notice.getNotice_content())
				.notice_created_time(notice.getNotice_created_time())
				.notice_view_count(notice.getNotice_view_count())
				.build();
	}
}
