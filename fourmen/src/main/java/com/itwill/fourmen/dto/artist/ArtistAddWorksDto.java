package com.itwill.fourmen.dto.artist;

import com.itwill.fourmen.domain.Artist_Works;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistAddWorksDto {

	private String userid;
	private String worksid;
	private String title;
	private String content_kor;
	private String content_eng;
	
	public Artist_Works toEntity() {
		
		return Artist_Works.builder()
				.userid(userid)
				.worksid(worksid)
				.title(title)
				.content_kor(content_kor)
				.content_eng(content_eng)
				.build();
	}
	
}
