package com.itwill.fourmen.dto.mymessage;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReadMyMessageDto {
	
	private int page;
	private int startingPage;
	private int postsPerPage;
	private String signedInUser;

}
