package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Comments;

public interface CommentDao {

	List<Comments> selectByWorksId(long worksid);	
	
	Long getMaxGroupNum (long worksid);
	
	Long getMaxGroupDepth (long c_group_level);
	
	Comments getComment(long comment_id);
	
	int updateParentCommentContentSetNull(long comment_id);
	
	int registerComment(Comments comments);
	
	int deleteCommentByCommentId(long comment_id);
	
	int deleteAllCommentsByWorksId(long worksid);
	
	int updateComment(Comments comments);
	
	int increaseAccAnswer(long parent_comment_id);
	
	int decreaseAccAnswer(long parent_comment_id);
}
