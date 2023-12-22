package com.itwill.fourmen.dto.post;

import com.itwill.fourmen.domain.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateDto {
	private String post_title;
	private String post_content;
	private String user_id;
	
	// PostCreateDTO 객체를 Post 모델(엔티티) 객체로 변환해서 리턴하는 메서드.
	// static일 필요가 없음. 사용자가 입력한 데이터의 타입이 Post이므로
	// 이를 DTO로 변환하는 과정이 필요함.
	public Post toEntity() {
		return Post.builder()
				.post_title(post_title)
				.post_content(post_content)
				.userid(user_id)
				.build();
	}
}
