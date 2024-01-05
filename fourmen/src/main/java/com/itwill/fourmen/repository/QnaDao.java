package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.board.SearchCriteriaAdminUser;
import com.itwill.fourmen.domain.Qna;
import com.itwill.fourmen.dto.post.QnaSearchDto;

public interface QnaDao {
	List<Qna> selectOrderByQnaIdDesc(QnaSearchDto dto);
	int readTotQnaPosts();
	int listCount(SearchCriteriaAdminUser scri);
	Qna selectByQnaId(long id);
	int qnaboard_insert(Qna qna);
	int qnaboard_delete(long id);
	int qnaboard_addView(long qna_id);
	List<Qna> qnaboard_search(QnaSearchDto dto);
	int searchTotNumber(QnaSearchDto dto);
	int update(Qna qna);
}
