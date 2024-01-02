package com.itwill.fourmen.dto.market;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PagingDto {
	
	private int startPage;	// 페이지 시작 포스트
	private int endPage;	// 페이지 끝 포스트
	private int totNumPages;	// 페이지 총 개수
	private int postsPerPage;	// 페이지 당 포스트 개수
	private int totNumPosts;	// 총 포스트 개수
	private int pagesShownInBar;	// 한 페이지당 개수

}
