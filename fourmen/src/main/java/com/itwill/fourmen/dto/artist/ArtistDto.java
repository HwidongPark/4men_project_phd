package com.itwill.fourmen.dto.artist;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Artist_Img;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDto {

	private String userid;
	private String nickname;
	private String artist_bio_kor;
	private String artist_bio_eng;
	private String original_img;
	private String saved_img;
	
	private MultipartFile upload_file;
	
	private String isFileDeleted;
	
	public static ArtistDto fromEntity(Artist artist, Artist_Img artist_Img) {
		return ArtistDto.builder()
				.userid(artist.getUserid())
				.nickname(artist.getNickname())
				.artist_bio_kor(artist.getArtist_bio_kor())
				.artist_bio_eng(artist.getArtist_bio_eng())
				.original_img(artist_Img.getOriginal_img())
				.saved_img(artist_Img.getSaved_img())
				.build();
	}
	
	public static ArtistDto fromEntity(Artist_Img artist_Img) {
		return ArtistDto.builder()
				.userid(artist_Img.getUserid())
				.original_img(artist_Img.getOriginal_img())
				.saved_img(artist_Img.getSaved_img())
				.build();
		
	}
	
	public static ArtistDto fromEntity(Artist artist) {
		return ArtistDto.builder()
				.userid(artist.getUserid())
				.nickname(artist.getNickname())
				.artist_bio_kor(artist.getArtist_bio_kor())
				.artist_bio_eng(artist.getArtist_bio_eng())
				.build();
	}
	
	public Artist artisToEntity() {
		return Artist.builder()
				.userid(userid)
				.nickname(nickname)
				.artist_bio_kor(artist_bio_kor)
				.artist_bio_eng(artist_bio_eng)
				.build();
	}
	
	public Artist_Img artistImgToEntity() {
		return Artist_Img.builder()
				.userid(userid)
				.original_img(original_img)
				.saved_img(saved_img)
				.build();
	}
}
