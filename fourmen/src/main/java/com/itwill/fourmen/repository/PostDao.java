package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Post;

public interface PostDao {
	List<Post> selectOrderByPostIdDesc();
	Post selectByPostId(long id);
	int freeboard_insert(Post post);
	int freeboard_update(Post posd);
	int freeboard_delete(long post_id);
	int freeboard_addView(long post_id);
//	List<Post> search(PostSearchDto dto);
}
