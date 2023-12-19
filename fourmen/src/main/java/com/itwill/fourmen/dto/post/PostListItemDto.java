package com.itwill.fourmen.dto.post;

import java.time.LocalDateTime;

import com.itwill.fourmen.domain.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostListItemDto {
	private Long post_num;
	private String title;
	private String userid;
	private LocalDateTime post_created_time;
	private Long post_view_count;
	
	// Post 모델(엔티티) 객체를 PostListItemDto 타입 객체로 변환해서 리턴.
	// static 이어야 함.
	public static PostListItemDto fromEntity(Post post) {
		return PostListItemDto.builder()
				.post_num(post.getPost_num())
				.title(post.getTitle())
				.userid(post.getUserid())
				.post_created_time(post.getPost_created_time())
				.post_view_count(post.getPost_view_count())
				.build();
	}
}
