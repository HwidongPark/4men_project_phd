package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Post;

public interface PostDao {
	List<Post> selectOrderByPostNumDesc();
	Post selectByPostId(long id);
	int insert(Post post);
	int freeboard_delete(long post_id);
	int faqboard_delete(long faq_id);
	int noticeboard_delete(long notice_id);
	int qnaboard_delete(long qna_id);
//	List<Post> search(PostSearchDto dto);
}
