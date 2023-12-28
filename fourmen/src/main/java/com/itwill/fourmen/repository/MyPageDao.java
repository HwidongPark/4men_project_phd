package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Message;
import com.itwill.fourmen.dto.mymessage.MyMessageDto;

public interface MyPageDao {
	
	// 메세지를 보내는 메서드
	int sendMessage(Message message);
	
	// 로그인돼 있는 유저가 받은 메세지 리스트를 받아오는 메서드
	List<Message> readMyMessage(String signedInUser);
	
	// 아규먼트로 메세지 id받아서 메세지를 반환
	Message readMyMessageById(Long id);
	
	// 아규먼트로 메세지Dto 객체 받아서 답장
	int replyMessage(MyMessageDto messageDto);
	
}
