package com.itwill.fourmen.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Market {

	Long workId;
	String userId;
	String title;
	String descriptionKor;
	String descriptionEng;
	Integer price;
	Integer yearCreated;
	String paintingSize;
	String isSold;
	String buyerId;
	LocalDateTime createdTime;
	Long views;
	Long likes;
	
}
