package com.itwill.fourmen.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {
	
	private Long id;
	private String sender;
	private String recipient;
	private Long workId;
	private String title;
	private String content;
	private Long priceOffered;
	private String isClosed;
	
}
