package com.itwill.fourmen.dto.comment;

import com.itwill.fourmen.domain.Comments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRegisterDto {

	private Long worksid;
	private String comment_content;
	private String comment_writer;
	
	public Comments toEntity() {
		return Comments.builder().worksid(worksid)
				.comment_writer(comment_writer)
				.comment_content(comment_content)
				.build();
	}
	
}
