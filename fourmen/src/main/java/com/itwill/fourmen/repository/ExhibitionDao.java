package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.exhibition.ExhibitionSearchDto;

public interface ExhibitionDao {
	List<Exhibition> selectAll();
	
	List<Exhibition> search(ExhibitionSearchDto dto);
}
