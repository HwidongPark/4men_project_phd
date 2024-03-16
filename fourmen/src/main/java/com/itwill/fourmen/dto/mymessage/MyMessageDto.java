package com.itwill.fourmen.dto.mymessage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itwill.fourmen.domain.Message;
import com.itwill.fourmen.dto.market.MarketPostDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class MyMessageDto {

	private Long id;
	private String sender;
	private String recipient;
	private Long workId;
	private String title;
	private String content;
	private Long price;	// 원 가격, 또는 답장할 때 상제방이 제시했'던' 가격
	private Long priceOffered;	// 새로 제시하는 가격
	private String isClosed;
	private Long replyTo;
	private List<MyMessageDto> replies = new ArrayList<>();
	private LocalDateTime timeSent;
	private MarketPostDto postDto;
	
	
	/**
	 * Message, MarketPostDto로부터 MyMessageDto를 만드는 메서드
	 * @param message
	 * @param postDto
	 * @return
	 */
	public static MyMessageDto fromEntity(Message message, MarketPostDto postDto) {
		
		return MyMessageDto.builder()
					.id(message.getId())
					.sender(message.getSender())
					.recipient(message.getRecipient())
					.workId(message.getWorkId())
					.title(message.getTitle())
					.content(message.getContent())
					.price(message.getPrice())
					.priceOffered(message.getPrice_offered())
					.isClosed(message.getIs_closed())
					.timeSent(message.getTime_sent())
					.replyTo(message.getReply_to())
					.replies(new ArrayList<MyMessageDto>())
					.postDto(postDto)
					.build();
		
	}
	
	
	/**
	 * Message로부터 MyMessageDto만드는 메서드
	 * @param message
	 * @return
	 */
	public static MyMessageDto fromEntity(Message message) {
		
		return MyMessageDto.builder()
					.id(message.getId())
					.sender(message.getSender())
					.recipient(message.getRecipient())
					.workId(message.getWorkId())
					.title(message.getTitle())
					.content(message.getContent())
					.price(message.getPrice())
					.priceOffered(message.getPrice_offered())
					.isClosed(message.getIs_closed())
					.timeSent(message.getTime_sent())
					.replyTo(message.getReply_to())
					.replies(new ArrayList<MyMessageDto>())
					.build();
		
	}
	
	
	
}
