package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.board.SearchCriteriaAdminUser;
import com.itwill.fourmen.domain.Post;
import com.itwill.fourmen.dto.post.PostListItemDto;
import com.itwill.fourmen.dto.post.PostSearchDto;

public interface PostDao {
	List<Post> selectOrderByPostIdDesc(SearchCriteriaAdminUser scri);
	int listCount(SearchCriteriaAdminUser scri);
	Post selectByPostId(long id);
	int freeboard_insert(Post post);
	int freeboard_update(Post posd);
	int freeboard_delete(long post_id);
	int freeboard_addView(long post_id);
	List<Post> freeboard_search(PostSearchDto dto);
}
