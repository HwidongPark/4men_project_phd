package com.itwill.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MarketCreateDto {
	String title;
	String description;	// kor따로있는데 걍 이렇게함
	List<MultipartFile> files;
	String userid;

}
