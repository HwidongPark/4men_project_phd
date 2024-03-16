package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.board.SearchCriteriaAdminUser;
import com.itwill.fourmen.domain.Notice;
import com.itwill.fourmen.dto.post.NoticeSearchDto;

public interface NoticeDao {
	List<Notice> selectOrderByNoticeIdDesc(SearchCriteriaAdminUser scri);
	int listCount(SearchCriteriaAdminUser scri);
	Notice selectByNoticeId(long id);
	int noticeboard_insert(Notice notice);
	int noticeboard_delete(long notice_id);
	int noticeboard_addView(long notice_id);
	List<Notice> noticeboard_search(NoticeSearchDto dto);
	int update(Notice notice);
}
