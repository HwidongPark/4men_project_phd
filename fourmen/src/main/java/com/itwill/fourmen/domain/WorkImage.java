package com.itwill.fourmen.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WorkImage {
	Long imgId;
	Long workId;
	String category;
	String originalFileName;
	String savedFileName;
	
	
}
