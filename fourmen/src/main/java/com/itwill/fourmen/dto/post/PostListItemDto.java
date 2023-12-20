package com.itwill.fourmen.dto.post;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.itwill.fourmen.domain.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostListItemDto {
	private Long post_id;
	private Long post_num;
	private String post_title;
	private String userid;
	private String post_content;
	private LocalDate post_created_time;
	private Long post_view_count;
	
	// Post 모델(엔티티) 객체를 PostListItemDto 타입 객체로 변환해서 리턴.
	// static 이어야 함.
	public static PostListItemDto fromEntity(Post post) {
		return PostListItemDto.builder()
				.post_id(post.getPost_id())
				.post_num(post.getPost_num())
				.post_title(post.getPost_title())
				.userid(post.getUserid())
				.post_content(post.getPost_content())
				.post_created_time(post.getPost_created_time())
				.post_view_count(post.getPost_view_count())
				.build();
	}
}
