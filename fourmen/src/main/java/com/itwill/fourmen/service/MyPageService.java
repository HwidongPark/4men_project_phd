package com.itwill.fourmen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Message;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.mymessage.MyMessageDto;
import com.itwill.fourmen.repository.MyPageDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageService {
	
	private final MyPageDao myPageDao;
	private final MarketService marketService;
	
	
	/**
	 * 메세지 객체를 아규먼트로 받아 메세지 보내는 서비스 메서드.
	 * @param message
	 * @return 성공시 1, 실패시 0
	 */
	public int sendMessage(Message message) {
		
		log.debug("sendMessage(message={})", message);
		
		int result = myPageDao.sendMessage(message);
		log.debug("메세지 보내기 결과={}", result);
		
		return result;
	}
	
	/**
	 * 로그인한 유저가 받은 메세지를 보여주는 서비스 메서드
	 * @param signedInUser
	 * @return 유저가 받은 메세지들을 리스트로 보여줌
	 */
	public List<Message> readMyMessage(String signedInUser) {
		log.debug("readMyMessage(signedInUser={})", signedInUser);
		
		List<Message> myMessageList = myPageDao.readMyMessage(signedInUser);
		log.debug("myMessageList={}", myMessageList);
		
		return myMessageList;
	}
	
	
	/**
	 * 메세지의 id를 아규먼트로 받아 해당 메세지를 읽어옴
	 * @param id
	 * @return MyMessageDto타입의 해당 메세지. 실패시 null
	 */
	public MyMessageDto readMymessage(Long id) {
		log.debug("readMyMessage(id={})", id);
		
		// 메세지를 받아옴
		Message message = myPageDao.readMyMessageById(id);
		log.debug("message={}", message);
		
		// 메세지와 관련된 게시글 정보를 받아옴
		MarketPostDto marketPostDto = marketService.readMarketPost(message.getWorkId());
		log.debug("marketPostDto = {}", marketPostDto);
		
		MyMessageDto messageDto =  MyMessageDto.fromEntity(message, marketPostDto);
		
		return messageDto;
	}
	
	
	
	public int replyMessage(MyMessageDto messageDto) {
		
		log.debug("replyMessage(messageDto={})", messageDto);
		
		int result = myPageDao.replyMessage(messageDto);
		log.debug("답장 결과={}", result);
		
		return result;		
	}
	
}
