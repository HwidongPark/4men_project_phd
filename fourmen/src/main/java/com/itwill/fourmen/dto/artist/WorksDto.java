package com.itwill.fourmen.dto.artist;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.Artist_Works;
import com.itwill.fourmen.domain.Artist_Works_Img;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorksDto {

	private Long imgid;
	private String userid;
	private Long worksid;
	private String title;
	private String content_kor;
	private String content_eng;
	
	private List<String> deletedImageIds;
	
	private String original_file;
	private String saved_file;
	
	private List<MultipartFile> upload_files;

	public Artist_Works worksToEntity() {
		return Artist_Works.builder()
				.userid(userid)
				.title(title)
				.worksid(worksid)
				.content_kor(content_kor)
				.content_eng(content_eng)
				.build();
	}
	
	public Artist_Works_Img worksImgToEntity() {
		return Artist_Works_Img.builder()
				.imgid(imgid)
				.worksid(worksid)
				.original_file(original_file)
				.saved_file(saved_file)
				.build();
	}
	
}
