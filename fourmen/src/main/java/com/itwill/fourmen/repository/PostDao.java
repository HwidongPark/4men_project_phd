package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Post;

public interface PostDao {
	List<Post> selectOrderByIdDesc();
	Post selectById(long id);
	int insert(Post post);
	int delete(long id);
//	List<Post> search(PostSearchDto dto);
}
