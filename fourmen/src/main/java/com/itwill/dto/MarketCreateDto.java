package com.itwill.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.Market;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MarketCreateDto {
	private Long workId;
	private String title;
	private String description;	// kor따로있는데 일단은 이렇게함
	private List<MultipartFile> files;
	private String userid;
	private int price;
	private double width;
	private double height;
	private double depth;
	private int yearCreated;
	
	public Market toEntity() {
		return Market.builder()
				.workId(this.workId)
				.title(this.title)
				.descriptionKor(description)
				.userId(this.userid)
				.price(this.price)
				.yearCreated(this.yearCreated)
				.paintingSize(String.format("%.1f x %.1f x %.1f cm", width, height, depth))
				.descriptionKor(this.description)
				.build();
				
	}
}

