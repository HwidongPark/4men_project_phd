package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Post;

public interface PostDao {
	List<Post> selectOrderByPostNumDesc();
	Post selectByPostId(long id);
	int freeboard_insert(Post post);
	int freeboard_delete(long post_id);
//	List<Post> search(PostSearchDto dto);
}
