package com.itwill.fourmen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.Message;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.market.PagingDto;
import com.itwill.fourmen.dto.mymessage.MyMessageDto;
import com.itwill.fourmen.dto.mymessage.ReadMyMessageDto;
import com.itwill.fourmen.repository.MyPageDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageService {
	
	private final MyPageDao myPageDao;
	private final MarketService marketService;
	
	private int postsPerPage = 3;
	private int pagesShownInBar = 3;
	
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
	 * 시작페이지, 끝페이지, 마지막 페이지 등 포함한 PagingDto를 반환
	 * @param page
	 * @return
	 */
	public PagingDto pagingMessages(int page, String signedInUser, String category) {
		int totNumOfMessages = 0;
		if (category.equals("received")) {
			totNumOfMessages = myPageDao.totNumOfMessages(signedInUser);
		} else if (category.equals("sent")) {
			totNumOfMessages = myPageDao.totNumOfSentMessages(signedInUser);
		}
		
		int startPage = (int) Math.ceil( ((double) page / pagesShownInBar) - 1 ) * pagesShownInBar + 1 ;
	
		int totNumPages = (int) Math.ceil((double) totNumOfMessages / postsPerPage);	// 총 페이지 개수
		int endPage = 0;
		if ((startPage + pagesShownInBar - 1) >= totNumPages) {
			endPage = totNumPages;
		} else {
			endPage = startPage + pagesShownInBar - 1;
		};
		
		PagingDto pagingDto = PagingDto.builder().startPage(startPage).endPage(endPage).totNumPages(totNumPages).pagesShownInBar(pagesShownInBar).build();
		
		return pagingDto;
	}
	
	
	/**
	 * 로그인한 유저가 받은 모든 메세지를 보여주는 서비스 메서드
	 * @param signedInUser
	 * @return 유저가 받은 메세지들을 리스트로 보여줌
	 */
	public List<Message> readMyMessage(String signedInUser, int page) {
		log.debug("readMyMessage(signedInUser={}, page={})", signedInUser, page);
		
		ReadMyMessageDto readMyMessageDto = ReadMyMessageDto.builder().signedInUser(signedInUser)
				.startingPage((page - 1) * postsPerPage)
				.postsPerPage(postsPerPage)
				.page(page)
				.build();
		
		List<Message> myMessageList = myPageDao.readMyMessage(readMyMessageDto);
		log.debug("myMessageList={}", myMessageList);
		
		return myMessageList;
	}
	
	
	/**
	 * 아규먼트로 signedInUser를 받아 해당 유저가 주고받은 메세지를 포함하는 메세지, 답변의 MyMessageDto 번들을 돌려줌
	 * 
	 * @param String userId
	 * @return 답변리스트를 필드로 포함하는 모든 첫 메세지 리스트
	 */
	public List<MyMessageDto> readMyMessageReplyBundle(String signedInUser) {
		log.debug("readMyMessageReplyBundle(signedInUser={})", signedInUser);
		
		// 첫 메세지 리스트 받음
		List<Message> firstMessages = myPageDao.readFirstMessages();
		List<MyMessageDto> firstMessageDtos =   firstMessages.stream().map((element) -> MyMessageDto.fromEntity(element)).toList();
		log.debug("firstMessageDtos={}", firstMessageDtos);
		
		// 첫 메세지들에 대한 답변메세지들을 순서대로 담음
		for (MyMessageDto firstMessageDto : firstMessageDtos) {
			
			// 답장들 다 가져옴
			List<Message> firstReply = myPageDao.readReply(firstMessageDto.getId());
			
			if (firstReply != null) {
				List<MyMessageDto> firstReplyDtos = firstReply.stream().map((element) -> MyMessageDto.fromEntity(element)).toList();
				firstMessageDto.getReplies().addAll(firstReplyDtos);
				
				for (MyMessageDto replyDto : firstReplyDtos) {
					processReplies(firstMessageDto, replyDto);
				}
				
				log.debug("답변포함 firstMessage={}", firstMessageDto);
				
			}	// if(reply!=null)끝
			
			
		}  // 첫메세지들 돌리는 for문 끝
		
		
		// 첫 메세지 또는 그에대한 답변 메세지들에 signedInUser가 포함돼 있는것만 추가
		List<MyMessageDto> firstMessagesOfSignedInUser = new ArrayList<>();
		for (MyMessageDto firstMessageDto : firstMessageDtos) {
			
			if (firstMessageDto.getRecipient().equals(signedInUser)) {	// 첫 메세지 수신인이 로그인한 유저인 경우
				firstMessagesOfSignedInUser.add(firstMessageDto);
			} else {	// 첫 메세지 수신인이 로그인한 유저가 아닌경우
				
				for (MyMessageDto reply : firstMessageDto.getReplies()) {
					
					if (reply.getRecipient().equals(signedInUser)) {
						firstMessagesOfSignedInUser.add(firstMessageDto);
						break;	// 답변의 수신인 중 로그인 유저 있으면 추가하고 reply loop탈출
					}
					
				}
				
			}
			
		}
		
		return firstMessagesOfSignedInUser;
	}
	
	
	
	
	
	/**
	 * readMyMessage를 보조하는 재귀함수. 모든 답변들을 찾아서 firstMessageDto의 답변리스트에 추가해줌
	 * @param firstMessageDto
	 * @param messageDto 첫 답변 MyMessageDto 리스트
	 */
	public void processReplies(MyMessageDto firstMessageDto, MyMessageDto messageDto) {
	    List<Message> replies = myPageDao.readReply(messageDto.getId());

	    if (replies != null) {
	        List<MyMessageDto> replyDtos = replies.stream()
	                .map(element -> MyMessageDto.fromEntity(element))
	                .toList();

	        firstMessageDto.getReplies().addAll(replyDtos);

	        for (MyMessageDto replyDto : replyDtos) {
	            processReplies(firstMessageDto, replyDto); // 재귀 호출
	        }
	    }
	}
	
	
	
	/**
	 * MyMessageDto를 아규먼트로 받아, 해당 메세지가 포함된 답변뭉치의 첫 메세지를 찾아줌
	 * @param MyMessageDto messageDto
	 * @return 답변뭉치의 첫 메세지dto를 반환
	 */
	public MyMessageDto findFirstMessage(MyMessageDto messageDto) {
		log.debug("findFirstMessage(messageDto={})", messageDto);
		
		if(messageDto.getReplyTo() != null) {
			
			while(messageDto.getReplyTo() != null) {
				
				Message parentMessage = myPageDao.findParentMessage(messageDto);
				MyMessageDto parentMessageDto = MyMessageDto.fromEntity(parentMessage);
				messageDto = parentMessageDto;
				
			}
			
			return messageDto;
			
		} else {
			return messageDto;
		}
			
	}
	
	
	/**
	 * MyMessageDto타입의 firstMessageDto를 아규먼트로 받아 해당 메세지의 답변들을 추가해서 반환해줌
	 * @param firstMessageDto
	 * @return firstMessageDto에 replies list를 추가해서 반환해줌
	 */
	public MyMessageDto findReplies(MyMessageDto firstMessageDto) {
		log.debug("findReplies(firstMessageDto={}))", firstMessageDto);				
		
		List<Message> firstReplies = myPageDao.readReply(firstMessageDto.getId());
		
		if (firstReplies != null) {
			List<MyMessageDto> firstReplyDtos = firstReplies.stream().map((element) -> MyMessageDto.fromEntity(element)).toList();
			firstMessageDto.getReplies().addAll(firstReplyDtos);
			
			for (MyMessageDto reply : firstReplyDtos) {
				processReplies(firstMessageDto, reply);
			}
		}
		
		
		return firstMessageDto;	
		
	}
	
	
	/**
	 * 답변들을 포함하고 있는 MyMessageDto 객체에서 로그인된 유저의 첫 수신 메세지를 반환.
	 * MyMessageDto는 로그인된 유저를 포함한 객체만 입력해야함.
	 * @param signedInUser  로그인된 유저가 포함된 객체여야함
	 * @return reply bundels에서 로그인된 유저가 처음 받은 메세지 반환
	 */
	public MyMessageDto firstReceivedMessage(String signedInUser, MyMessageDto firstMessageDto) {
		
		log.debug("firstReceivedMessage(signedInUser={}, firstMessageDto={})", signedInUser, firstMessageDto);
		
		if (firstMessageDto.getRecipient().equals(signedInUser)) {
			return firstMessageDto;
		} else {
			for (MyMessageDto reply : firstMessageDto.getReplies()) {
				if (reply.getRecipient().equals(signedInUser)) {
					return reply;
				}
			}
		}
		
		return null;	// 유저가 잘못된 firstMessageDto를 입력했을 경우..
	}
	
	
	/**
	 * 답변들을 포함하고 있는 MyMessageDto 객체에서 로그인된 유저의 모든 수신 메세지를 반환
	 * @param signedInUser
	 * @param firstMessageDto
	 * @return
	 */
	public List<MyMessageDto> receivedMessagesList(String signedInUser, MyMessageDto firstMessageDto) {
		
		log.debug("receivedMessageList(signedInUser={}, firstMessageDto={})", signedInUser, firstMessageDto);
		
		List<MyMessageDto> receivedMessages = new ArrayList<>();
		if (firstMessageDto.getRecipient().equals(signedInUser)) {
			receivedMessages.add(firstMessageDto);
		}
		
		for (MyMessageDto reply : firstMessageDto.getReplies()) {
			if (reply.getRecipient().equals(signedInUser)) {
				receivedMessages.add(reply);
			}
		}
		
		return receivedMessages;
	}
	
	
	// TODO: 내가 보낸 메세지들을 전부 가져옴
	/**
	 * 로그인한 유저를 아규먼트로 받아서, 그 유저가 보낸 메세지들의 리스트를 가져옴
	 * @param signedInUser
	 * @return
	 */
	public List<MyMessageDto> readSentMessage(String signedInUser, int page) {
		log.debug("readSentMessage(signedInUser={}, page={})", signedInUser, page);
		
		ReadMyMessageDto readMyMessageDto = ReadMyMessageDto.builder()
						.signedInUser(signedInUser)
						.page(page)
						.postsPerPage(postsPerPage)
						.startingPage( (page - 1) * postsPerPage  )
						.build();
		
		List<Message> sentMessages = myPageDao.readSentMessage(readMyMessageDto);
		log.debug("sentMessages={}", sentMessages);
		
		// 내가 보낸 메세지들에 관련된 Post들을 추가
		List<MyMessageDto> sentMessageDtos = sentMessages.stream().map((element) -> MyMessageDto.fromEntity(element)).toList();
		for (MyMessageDto sentMessageDto : sentMessageDtos) {
			
			MarketPostDto postDto = marketService.readMarketPost(sentMessageDto.getWorkId());
			sentMessageDto.setPostDto(postDto);
		}
		
		return sentMessageDtos;
		
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
	
	
	
	/**
	 * 답장하는 Service Method
	 * @param messageDto
	 * @return
	 */
	public int replyMessage(MyMessageDto messageDto) {
		
		log.debug("replyMessage(messageDto={})", messageDto);
		
		int result = myPageDao.replyMessage(messageDto);
		log.debug("답장 결과={}", result);
		
		return result;		
	}
	
	
	/**
	 * 거래확정하는 서비스 메서드. message를 아규먼트로 받아서 거래확정 성공 시 1을, 실패시 0을 리턴
	 * @param message
	 * @return 거리확정 성공시 1, 실패시 0 리턴
	 */
	public int confirmDeal(Message message) {
		
		log.debug("confirmDeal(message={})", message);
		
		int result = myPageDao.confirmDeal(message);
		log.debug("거래 확정 결과={}", result);
		
		return result;
	}
	
}
