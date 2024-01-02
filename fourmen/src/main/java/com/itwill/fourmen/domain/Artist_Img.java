package com.itwill.fourmen.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist_Img {

	private String userid;
	private String artist_o_img;
	private String artist_s_img;
	
}
