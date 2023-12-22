package com.itwill.fourmen.dto.artist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itwill.fourmen.domain.Artist_Works;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistWorksListItemDto {

	private String userid;
	private String worksid;
	private String title;
	private LocalDateTime created_date;
	
	
	public String getFormattedCreatedDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return this.created_date.format(formatter);
	}
	
	
	public static ArtistWorksListItemDto fromEntity(Artist_Works artist_works) {
		
		return ArtistWorksListItemDto.builder().userid(artist_works.getUserid())
				.worksid(artist_works.getWorksid())
				.title(artist_works.getTitle())
				.created_date(artist_works.getCreated_date())
				.build();
		
	}
	
}
