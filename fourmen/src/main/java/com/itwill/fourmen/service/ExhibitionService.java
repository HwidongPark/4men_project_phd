package com.itwill.fourmen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.itwill.fourmen.board.Criteria;
import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.domain.Exhibition;

import com.itwill.fourmen.repository.ExhibitionDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExhibitionService {

	private final ExhibitionDao exhibitionDao;
	
	
	    public List<Exhibition> list(SearchCriteria scri){
	    	return exhibitionDao.list(scri);
	    }

	    public int listCount(SearchCriteria scri) {

	        return exhibitionDao.listCount(scri);

	    }
	    


}
