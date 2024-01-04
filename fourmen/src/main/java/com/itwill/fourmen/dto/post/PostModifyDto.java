package com.itwill.fourmen.dto.post;

import com.itwill.fourmen.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostModifyDto {
	private String post_title;
	private String post_content;
	private Long post_id;
	
	public Post toEntity() {
		return Post.builder()
				.post_id(post_id)
				.post_title(post_title)
				.post_content(post_content)
				.build();
	}
}