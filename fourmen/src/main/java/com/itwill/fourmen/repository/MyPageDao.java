package com.itwill.fourmen.repository;

import java.util.List;

import com.itwill.fourmen.domain.Message;
import com.itwill.fourmen.dto.mymessage.MyMessageDto;
import com.itwill.fourmen.dto.mymessage.ReadMyMessageDto;

public interface MyPageDao {
	
	// 메세지를 보내는 메서드
	int sendMessage(Message message);
	
	// 로그인 유저가 받은 총 메세지 개수
	int totNumOfMessages(String signedInUser);
	
	// 로그인돼 있는 유저가 받은 메세지 리스트를 받아오는 메서드
	List<Message> readMyMessage(ReadMyMessageDto readMyMessageDto);
	
	// 메세지, 메세지 답변 뭉치의 첫 메세지들만 가져옴
	List<Message> readFirstMessages();
	
	// 해당 메세지에 대한 답변을 받아오는 메서드
	List<Message> readReply(Long id);
	
	// 해당 메세지의 부모 메세지를 찾아오는 메서드
	Message findParentMessage(MyMessageDto messageDto);
	
	// 로그인한 유저가 보낸 메세지들의 리스트를 받아오는 메서드
	List<Message> readSentMessage(ReadMyMessageDto readMyMessageDto);
	
	// 로그인한 유저가 보낸 메세지들의 개수를 받아오는 메서드
	int totNumOfSentMessages(String signedInUser);
	
	// 아규먼트로 메세지 id받아서 메세지를 반환
	Message readMyMessageById(Long id);
	
	// 아규먼트로 메세지Dto 객체 받아서 답장
	int replyMessage(MyMessageDto messageDto);
	
	// 거래확정하는 Dao 메서드
	int confirmDeal(Message message);
	
	// 유저 탈퇴시 유저가 보낸사람인 경우 탈퇴한 회원으로 메세지 테이블 내 변경
	int updateMessageForDeletedUserSender(String signedInUser);
	
	// 유저 탈퇴 시 유저가 받는사람인 경우 탈퇴한 회원으로 메세지 테이블 내 변경
	int updateMessageForDeletedUserRecipient(String signedInUser);
	
}
