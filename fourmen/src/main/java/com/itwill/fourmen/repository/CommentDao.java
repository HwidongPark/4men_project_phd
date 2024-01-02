package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Comments;

public interface CommentDao {

	List<Comments> selectByWorksId(long worksid);
	
	Comments getComment(long comment_id);
	
	int registerComment(Comments comments);
	
	int deleteCommentByCommentId(long comment_id);
	
	int deleteAllCommentsByWorksId(long worksid);
	
	int updateComment(Comments comments);
}
