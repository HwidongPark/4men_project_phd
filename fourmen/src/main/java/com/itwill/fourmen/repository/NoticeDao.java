package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Notice;

public interface NoticeDao {
	List<Notice> selectOrderByNoticeNumDesc();
	Notice selectByNoticeId(long id);
	int noticeboard_insert(Notice notice);
	int noticeboard_delete(long notice_id);
//	List<Notice> search(NoticeSearchDto dto);
}
