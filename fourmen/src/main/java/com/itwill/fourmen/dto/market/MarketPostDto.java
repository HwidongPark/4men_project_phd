package com.itwill.fourmen.dto.market;

import java.time.LocalDateTime;
import java.util.List;

import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.User;
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
	private Artist artist;
	
	/**
	 * 게시글 객체와 게시글의 이미지 모델을 합쳐 DTO만들기
	 * @param market
	 * @param workImages
	 * @return
	 */
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
	
	/**
	 * 게시글 모델과 게시글의 이미지 모델, 유저모델을 합쳐 DTO만들기
	 * 유저모델에서는 유저의 닉네임을 가져올것임
	 * @param market
	 * @param workImages
	 * @param user
	 * @param artist
	 * @return
	 */
	public static MarketPostDto fromEntity(Market market, List<WorkImage> workImages, User user, Artist artist) {
		
		return MarketPostDto.builder()
				.userId(market.getUserId())
				.nickname(user.getNickname())
				.workId(market.getWorkId())
				.title(market.getTitle())
				.descriptionKor(market.getDescriptionKor())
				.price(market.getPrice())
				.yearCreated(market.getYearCreated())
				.paintingSize(market.getPaintingSize())
				.createdTime(market.getCreatedTime())
				.isSold(market.getIsSold())
				.buyerId(market.getBuyerId())
				.views(market.getViews())
				.likes(market.getLikes())
				.workImages(workImages)
				.artist(artist)
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
					.buyerId(buyerId)
					.createdTime(createdTime)
					.views(views)
					.likes(likes)
					.build();
	}
	
	
	
}
