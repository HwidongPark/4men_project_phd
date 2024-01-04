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
	private Long c_group; // 댓글 그룹
	private Long c_group_level; // 각 댓글에 생성될 레벨 
	private Long c_group_depth; // 각 댓글에 달린 댓글의 층계
	private Long parent_comment_id; // 부모의 댓글 아이디
	private Long acc_comment; // 누적된 댓글의 수
	private Timestamp modified_time; // 수정 시간
	
	public static CommentListDto fromEntity(Comments comment) {
		return CommentListDto.builder()
				.comment_id(comment.getComment_id())
				.comment_content(comment.getComment_content())
				.comment_writer(comment.getComment_writer())
				.c_group(comment.getC_group())
				.c_group_level(comment.getC_group_level())
				.c_group_depth(comment.getC_group_depth())
				.parent_comment_id(comment.getParent_comment_id())
				.acc_comment(comment.getAcc_comment())
				.modified_time(Timestamp.valueOf(comment.getModified_time()))
				.build();
	}
}
