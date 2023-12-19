package com.itwill.dto;

import java.time.LocalDateTime;
import java.util.List;

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
	
	
}
