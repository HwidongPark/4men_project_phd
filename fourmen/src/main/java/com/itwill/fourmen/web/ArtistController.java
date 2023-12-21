package com.itwill.fourmen.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Artist_Works;
import com.itwill.fourmen.dto.artist.ArtistUpdateDto;
import com.itwill.fourmen.dto.artist.ArtistWorksImgListItemDto;
import com.itwill.fourmen.dto.artist.ArtistWorksListItemDto;
import com.itwill.fourmen.service.ArtistService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/artist")
public class ArtistController {

	private final ArtistService artistService;
	
	
	@GetMapping("/artist_details")
    public void details(@RequestParam(name="userid") String userid, Model model) {
    	log.debug("details(userid ={})",userid);
    	
    	// Artist 개개인의 작품 리스트 (페이지의 하단)
    	List<ArtistWorksListItemDto> worksList = artistService.readWorks(userid);
    	model.addAttribute("worksList", worksList);
    	
    	// Artist 개개인 소개 및 정보 (페이지의 상단)
    	Artist artist = artistService.details(userid);
    	model.addAttribute("artist", artist);
    }
	
	@GetMapping("/artist_works")
	public void works(@RequestParam(name="worksid") String worksid, Model model) {
		log.debug("works()");
		
		// Artist 개개인의 작품 정보를 뽑아와야함...
		Artist_Works artist_works = artistService.worksDetails(worksid);
		model.addAttribute("artist_works", artist_works);
		
		// Artist 개개인의 작품에서 사진을 가져와야함...		
		List<ArtistWorksImgListItemDto> worksImgList = artistService.getWorksImg(worksid);
		model.addAttribute("worksImgList", worksImgList);
	}
	
	// Artist 개개인 정보 수정하는 페이지로 이동함.
	@GetMapping("/artist_modify")
	public void modify(@RequestParam(name = "userid") String userid, Model model) {
		log.debug("modify()");
		log.debug("Get Artist_Modify(USERID = {})", userid);
		
		Artist artist = artistService.details(userid);
		model.addAttribute("artist" ,artist);
	}
	
	// Artist 개개인 정보 수정후 POST 방식으로 전달. 
	@PostMapping("/artist_modify")
	public String artistUpdate(ArtistUpdateDto dto) throws IllegalStateException, IOException {
		log.debug("artistUpdate()");
		log.debug("artistUpadate(ArtistUpdateDto dto) = {}", dto);
		log.debug(dto.getArtist_bio_kor());
		log.debug(dto.getArtist_bio_eng());
		
		final String UPLOAD_PATH = "/Users/ojng/4men_team_project/fourmen/src/main/webapp/static/images/char/";
		
		if(dto.getUpload_file() != null && !dto.getUpload_file().isEmpty()) {
			String originalFileName = getFileName(dto.getUpload_file());
			String fileExtension = getFileExtension(originalFileName);
			String fileName = UUID.randomUUID().toString() + fileExtension;
			
			String filePath = UPLOAD_PATH + fileName;
			log.debug("ArtistUpdate(filePath = {})", filePath);
			
			String artist_img  = fileName;
			log.debug("ArtistUpdate(fileName = {})", artist_img);
			
			dto.setArtist_img(artist_img);
			
			int result = artistService.updateArtist(dto);
			
			log.debug("ArtistUpdate result = {}", result);
			
			File destFile = new File(filePath);
			
			dto.getUpload_file().transferTo(destFile);
			
			log.debug("UPLOAD SUCCESS....!");
		} else {
			log.debug("UPLOAD FAIL PLEASE CHECK THE CODE...");
		}
		
		return "redirect:/artist/artist_details";
	}// end of artistUpdate method..
	
	// 업로드된 파일 이름을 변경하기 위한 메서드
	private String getFileName(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        
        originalFileName = originalFileName.substring(originalFileName.lastIndexOf("/")+1);
        
        return originalFileName;
    }
	
	// 업로드된 파일 이름을 변경하기 위한 메서드
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if(dotIndex >= 0 && dotIndex < fileName.length() -1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }
}
