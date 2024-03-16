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
	private Long c_group;
	private Long c_group_level;
	private Long c_group_depth;
	private Long parent_comment_id;
	private Long acc_comment;
	
	public Comments toEntity() {
		return Comments.builder().worksid(worksid)
				.c_group(c_group)
				.c_group_level(c_group_level)
				.c_group_depth(c_group_depth)
				.comment_writer(comment_writer)
				.comment_content(comment_content)
				.parent_comment_id(parent_comment_id)
				.acc_comment(acc_comment)
				.build();
	}
	
}
