package com.itwill.fourmen.dto.market;

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
	private String userId;
	private int price;
	private Double width;
	private Double height;
	private Double depth;
	private Integer yearCreated;
	
	public Market toEntity() {
		return Market.builder()
				.workId(this.workId)
				.title(this.title)
				.descriptionKor(description)
				.userId(this.userId)
				.price(this.price)
				.yearCreated(this.yearCreated)
				.paintingSize(String.format("%.1f x %.1f x %.1f cm", width, height, depth))
				.descriptionKor(this.description)
				.build();
				
	}
}

