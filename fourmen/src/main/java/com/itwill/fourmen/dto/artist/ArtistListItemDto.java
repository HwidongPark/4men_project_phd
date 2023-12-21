package com.itwill.fourmen.dto.artist;

import com.itwill.fourmen.domain.Artist;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistListItemDto {

	private String userid;
	private String artist_bio_kor;
	private String artist_bio_eng;
	private String artist_img;
	
	public static ArtistListItemDto fromEntity(Artist artist) {
		
		return ArtistListItemDto.builder().userid(artist.getUserid())
				.artist_bio_kor(artist.getArtist_bio_kor())
				.artist_bio_eng(artist.getArtist_bio_eng())
				.artist_img(artist.getArtist_img())
				.build();
		
	}
	
}
