package com.itwill.fourmen.dto.artist;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.Artist_Works_Img;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistAddWorksImgDto {

	private String worksid;
	private String category;
	private List<MultipartFile> original_file;
	private String saved_file;
	
}
