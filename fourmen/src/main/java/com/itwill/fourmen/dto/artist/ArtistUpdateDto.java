package com.itwill.fourmen.dto.artist;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.Artist;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistUpdateDto {

	private String userid;
	private String artist_bio_kor;
	private String artist_bio_eng;
	private String artist_img;
	
	private MultipartFile upload_file;
	
	public Artist toEntity() {
		return Artist.builder()
				.userid(userid)
				.artist_bio_kor(artist_bio_kor)
				.artist_bio_eng(artist_bio_eng)
				.artist_img(artist_img)
				.build();
	}
	
}
