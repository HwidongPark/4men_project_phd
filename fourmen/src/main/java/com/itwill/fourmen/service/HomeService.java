package com.itwill.fourmen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.repository.ExhibitionDao;
import com.itwill.fourmen.repository.HomeDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class HomeService {
	
	private final HomeDao homedao;
	
	public List<Exhibition>homeexhibition(){
		log.debug("service exhibitionhome ok");
		return homedao.homeexhibition();
	}
}
