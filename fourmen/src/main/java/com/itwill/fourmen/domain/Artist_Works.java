package com.itwill.fourmen.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist_Works {

	private String userid;
	private Long worksid;
	private String title;
	private String content_kor;
	private String content_eng;
	private LocalDateTime created_date;
	
}
