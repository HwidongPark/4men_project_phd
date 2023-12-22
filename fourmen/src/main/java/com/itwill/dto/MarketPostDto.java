package com.itwill.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.WorkImage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarketPostDto {
	
	private String userId;
	private Long workId;
	private String name;
	private String nickname;
	private String title;
	private String descriptionKor;
	private String descriotionEng;
	private List<WorkImage> workImages;
	private LocalDateTime createdTime;
	private Long views;
	private Long likes;
	private Integer price;
	private Integer yearCreated;
	private String paintingSize;
	private String isSold;
	private String buyerId;
	
	
	public static MarketPostDto fromEntity(Market market, List<WorkImage> workImages) {
		
		return MarketPostDto.builder()
				.userId(market.getUserId())
				.workId(market.getWorkId())
				.title(market.getTitle())
				.descriptionKor(market.getDescriptionKor())
				.price(market.getPrice())
				.yearCreated(market.getYearCreated())
				.paintingSize(market.getPaintingSize())
				.createdTime(market.getCreatedTime())
				.isSold(market.getIsSold())
				.views(market.getViews())
				.likes(market.getLikes())
				.workImages(workImages)
				.build();		
	}
	
	
	public Market toEntity() {
		return Market.builder()
					.workId(workId)
					.userId(userId)
					.title(title)
					.descriptionKor(descriptionKor)
					.descriptionEng(descriotionEng)
					.price(price)
					.yearCreated(yearCreated)
					.paintingSize(paintingSize)
					.isSold(isSold)
					.buyerId(likes)
					.createdTime(createdTime)
					.views(views)
					.likes(likes)
					.build();
	}
	
	
	
}
