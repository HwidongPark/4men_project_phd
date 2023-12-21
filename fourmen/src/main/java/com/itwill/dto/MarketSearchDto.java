package com.itwill.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MarketSearchDto {
	private String minPrice;
	private String maxPrice;
	private String searchCategory;
	private String keyword;
}
