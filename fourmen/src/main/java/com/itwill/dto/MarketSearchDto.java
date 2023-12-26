package com.itwill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
public class MarketSearchDto {
	private String minPrice;
	private String maxPrice;
	private String searchCategory;
	private String keyword;
	private Integer page;
	private Integer StartingPost;
	private Integer postsPerPage;
}
