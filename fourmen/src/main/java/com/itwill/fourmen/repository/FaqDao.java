package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Faq;
import com.itwill.fourmen.dto.post.FaqSearchDto;

public interface FaqDao {
	List<Faq> selectOrderByFaqIdDesc();
	Faq selectByFaqId(long id);
	int faqboard_insert(Faq faq);
	int faqboard_delete(long id);
	int faqboard_addView(long faq_id);
	List<Faq> faqboard_search(FaqSearchDto dto);
}
