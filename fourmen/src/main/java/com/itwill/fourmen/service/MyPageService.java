package com.itwill.fourmen.service;

import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Message;
import com.itwill.fourmen.repository.MyPageDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageService {
	
	private final MyPageDao myPageDao;
	
	public int sendMessage(Message message) {
		
		log.debug("sendMessage(message={})", message);
		
		int result = myPageDao.sendMessage(message);
		log.debug("메세지 보내기 결과={}", result);
		
		return result;
	}
	
	
}
