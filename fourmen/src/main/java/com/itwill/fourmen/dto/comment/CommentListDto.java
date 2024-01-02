package com.itwill.fourmen.dto.comment;

import java.sql.Timestamp;

import com.itwill.fourmen.domain.Comments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentListDto {

	private Long comment_id;
	private String comment_content;
	private String comment_writer;
	private Timestamp modified_time;
	
	public static CommentListDto fromEntity(Comments comment) {
		return CommentListDto.builder()
				.comment_id(comment.getComment_id())
				.comment_content(comment.getComment_content())
				.comment_writer(comment.getComment_writer())
				.modified_time(Timestamp.valueOf(comment.getModified_time()))
				.build();
	}
}
