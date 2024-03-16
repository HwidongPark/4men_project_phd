package com.itwill.fourmen.dto.market;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.WorkImage;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MarketPostRestDto {
	
	private String userId;
	private Long workId;
	private String name;
	private String nickname;
	private String title;
	private String descriptionKor;
	private String descriotionEng;
	private List<WorkImage> workImages;
	private Timestamp createdTime;
	private Long views;
	private Long likes;
	private Integer price;
	private Integer yearCreated;
	private String paintingSize;
	private String isSold;
	private String buyerId;
	
	
	public static MarketPostRestDto fromEntity(Market market, List<WorkImage> workImages) {
		
		return MarketPostRestDto.builder()
				.userId(market.getUserId())
				.workId(market.getWorkId())
				.title(market.getTitle())
				.descriptionKor(market.getDescriptionKor())
				.price(market.getPrice())
				.yearCreated(market.getYearCreated())
				.paintingSize(market.getPaintingSize())
				.createdTime(Timestamp.valueOf(market.getCreatedTime()))
				.isSold(market.getIsSold())
				.views(market.getViews())
				.likes(market.getLikes())
				.workImages(workImages)
				.build();		
	}
	
	
}
