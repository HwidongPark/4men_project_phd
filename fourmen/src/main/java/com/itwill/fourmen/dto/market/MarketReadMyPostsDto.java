package com.itwill.fourmen.dto.market;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MarketReadMyPostsDto {

	private String signedInUser;
	private int startingPost;
	private int postsPerPage;

}
