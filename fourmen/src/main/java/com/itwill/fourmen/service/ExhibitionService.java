package com.itwill.fourmen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.exhibition.ExhibitionSearchDto;
import com.itwill.fourmen.repository.ExhibitionDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExhibitionService {

	private final ExhibitionDao exhibitionDao;
	
	public List<Exhibition> read(){
		List<Exhibition> exhibition =exhibitionDao.selectAll();
		log.debug("포스트목록개수={}",exhibition.size());
		
		return exhibition;
	}
	
	public List<Exhibition> search(ExhibitionSearchDto dto){
		List<Exhibition> exhibition =exhibitionDao.search(dto);
		log.debug("검색결과={}",exhibition);
		
		
		return exhibition;
		
	}
}
