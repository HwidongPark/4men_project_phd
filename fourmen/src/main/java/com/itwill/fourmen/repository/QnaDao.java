package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Qna;

public interface QnaDao {
	List<Qna> selectOrderByQnaIdDesc();
	Qna selectByQnaId(long id);
	int qnaboard_insert(Qna qna);
	int qnaboard_delete(long id);
	int qnaboard_addView(long qna_id);
//	List<Qna> search(QnaSearchDto dto);
}
