package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Faq;

public interface FaqDao {
	List<Faq> selectOrderByFaqNumDesc();
	Faq selectByFaqId(long id);
	int insert(Faq faq);
	int delete(long id);
//	List<Faq> search(FaqSearchDto dto);
}
