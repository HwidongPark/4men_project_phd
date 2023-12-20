package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Notice;

public interface NoticeDao {
	List<Notice> selectOrderByNoticeNumDesc();
	Notice selectByNoticeId(long id);
	int insert(Notice notice);
	int delete(long id);
//	List<Notice> search(NoticeSearchDto dto);
}
