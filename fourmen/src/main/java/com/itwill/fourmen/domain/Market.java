package com.itwill.fourmen.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Market {

	Long workId;
	Long userId;
	String descriptionKor;
	String descriptionEng;
	Integer price;
	Integer yearCreated;
	String paintingSize;
	String isSold;
	Long buyerId;
	LocalDateTime createdTime;
	int views;
	int likes;

}
