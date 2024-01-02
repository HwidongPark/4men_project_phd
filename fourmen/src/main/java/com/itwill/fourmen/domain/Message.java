package com.itwill.fourmen.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Message {
	
	private Long id;
	private String sender;
	private String recipient;
	private Long workId;
	private String title;
	private String content;
	private Long price;
	private Long price_offered;
	private String is_closed;
	private Long reply_to;
	private LocalDateTime time_sent;
	
}
