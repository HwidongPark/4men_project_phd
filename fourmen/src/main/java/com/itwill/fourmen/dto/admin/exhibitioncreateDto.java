package com.itwill.fourmen.dto.admin;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.dto.artist.ArtistUpdateDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class exhibitioncreateDto {
	private String id;
	private String name;
	private String location;
	private LocalDate startdate;
	private LocalDate enddate;
	private String photo;
	private String site;
	private List<MultipartFile> upload_file;
	
	public Exhibition toEntity() {
		return Exhibition.builder()
				.name(name).location(location).startdate(startdate).enddate(enddate).photo(photo).site(site)
				.build();
	}
}
