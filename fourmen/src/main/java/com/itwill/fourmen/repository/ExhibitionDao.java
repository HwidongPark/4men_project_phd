package com.itwill.fourmen.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itwill.fourmen.board.Criteria;
import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.domain.Exhibition;


public interface ExhibitionDao {

	
	List<Exhibition> list(SearchCriteria scri);

    int listCount(SearchCriteria scri);
    



}
