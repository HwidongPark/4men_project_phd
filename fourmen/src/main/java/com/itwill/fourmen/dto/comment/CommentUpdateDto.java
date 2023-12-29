package com.itwill.fourmen.dto.comment;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.itwill.fourmen.domain.Comments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateDto {

	private Long comment_id;
	private String comment_content;
	
	public Comments toEntity() {
		return Comments.builder()
				.comment_id(comment_id)
				.comment_content(comment_content)
				.build();
	}
}
