package com.itwill.fourmen.dto.artist;

import com.itwill.fourmen.domain.Artist_Works_Img;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistWorksImgListItemDto {

	private Long imgid;
	private String worksid;
	private String category;
	private String original_file;
	private String saved_file;
	
	
	public static ArtistWorksImgListItemDto fromEntity(Artist_Works_Img artist_works_img) {
		
		return ArtistWorksImgListItemDto.builder().imgid(artist_works_img.getImgid())
				.worksid(artist_works_img.getWorksid())
				.category(artist_works_img.getCategory())
				.original_file(artist_works_img.getOriginal_file())
				.saved_file(artist_works_img.getSaved_file())
				.build();
		
	}
}
