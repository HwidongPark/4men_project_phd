package com.itwill.fourmen.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WishList {
	
	private String userId;
	private String category;
	private Long workId;
	
}
