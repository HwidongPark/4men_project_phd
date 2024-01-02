package com.itwill.fourmen.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist_Works_Img {

	private Long imgid;
	private Long worksid;
	private String category;
	private String original_file;
	private String saved_file;
	
}
