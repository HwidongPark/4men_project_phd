package com.itwill.fourmen.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.itwill.fourmen.domain.Artist;
import com.itwill.fourmen.domain.Artist_Img;
import com.itwill.fourmen.domain.Artist_Works;
import com.itwill.fourmen.domain.Artist_Works_Img;
import com.itwill.fourmen.dto.artist.ArtistAddWorksDto;
import com.itwill.fourmen.dto.artist.ArtistAddWorksImgDto;
import com.itwill.fourmen.dto.artist.ArtistDto;
import com.itwill.fourmen.dto.artist.ArtistWorksImgListItemDto;
import com.itwill.fourmen.dto.artist.ArtistWorksListItemDto;
import com.itwill.fourmen.dto.artist.WorksDto;
import com.itwill.fourmen.service.ArtistService;
import com.itwill.fourmen.util.RequestContextHelper;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
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
    	
    	// Artist 개인의 사진 정보
    	Artist_Img artist_img = artistService.artistImgDetails(userid);
    	model.addAttribute("artist_img", artist_img);
    }
	
	@GetMapping("/artist_works")
	public void works(@RequestParam(name="worksid") Long worksid, Model model) {
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
		
		Artist_Img artist_Img = artistService.artistImgDetails(userid);
		model.addAttribute("artist_img", artist_Img);
	}
	
	// Artist 개개인 정보 수정후 POST 방식으로 전달. 
	@PostMapping(path = "/artist_modify")
	public String artistUpdate(@ModelAttribute ArtistDto dto, HttpServletRequest request) throws IllegalStateException, IOException {
		log.debug("artistUpdate()");
		log.debug("artistUpadate(ArtistUpdateDto dto) = {}", dto);
		
		//이거 안되네....
		//ServletContext context = RequestContextHelper.getCurrentServletContext();
		//String sDirectory = context.getRealPath("/static/imgaes/char/");
		
		String sDirectory = request.getServletContext().getRealPath("/static/images/char");
		log.debug("ArtistController artistUpdate - sDirectory = {}",sDirectory);
		
		artistService.updateArtist(dto, sDirectory);
		
		return "redirect:/artist/artist_details?userid=" + dto.getUserid();
	}// end of artistUpdate method..
	
	// Artist 작품 추가하는 페이지 get 방식으로 가져오기...
	@GetMapping("/artist_add_works")
	public void artistAddWorks(@RequestParam(name = "userid") String userid, Model model) {
		log.debug("artistAddWorks()");
		
		Artist artist = artistService.details(userid);
		model.addAttribute("artist", artist);
	}
	
	// Artist 작품 추가하는 페이지 POST 방식으로 입력 받아서 전달...
	@PostMapping("/artist_add_works")
	public String artistAddWorks(@ModelAttribute ArtistAddWorksDto addWokrsDto, @ModelAttribute WorksDto worksDto, HttpServletRequest request) throws IllegalStateException, IOException {
		log.debug("artistAddWorks()");
		log.debug("artistAddWorks ArtistAddWorksDto = {}", addWokrsDto);
		log.debug("artistAddWorks WorksDto = {}", worksDto);

		artistService.addWorks(addWokrsDto);
		
		String sDirectory = request.getServletContext().getRealPath("/static/images/works");
		log.debug("artistAddWorks - sDirectory = {}", sDirectory);
		
		artistService.insertWorksImg(worksDto, sDirectory);
		
		return "redirect:/artist/artist_details?userid=" + worksDto.getUserid() ;
	}
	
	@GetMapping("/delete")
	public String deleteWorks(@RequestParam(name="worksid") Long worksid, HttpServletRequest request) {
		log.debug("Get delete worksid, userid = { , }", worksid);
		
		String sDirectory = request.getServletContext().getRealPath("/static/images/works");
		
		String userid = artistService.getUseridByWorksId(worksid);
		
		int result =  artistService.deleteWorks(worksid, sDirectory);
		
		log.debug("deleteWorks result = {}", result);
		
		return "redirect:/artist/artist_details?userid=" + userid;
	}
	
	@GetMapping("/works_modify")
	public void worksModify(@RequestParam(name = "worksid") Long worksid, Model model) {
		log.debug("worksModify");
		
		Artist_Works artist_works = artistService.worksDetails(worksid);
		model.addAttribute("artist_works", artist_works);
		
		List<ArtistWorksImgListItemDto> artist_works_img_list = artistService.getWorksImg(worksid);
		model.addAttribute("artist_works_img_list", artist_works_img_list);
	}
	
	@PostMapping(value = "/works_modify", consumes = {"multipart/form-data"})
	public void worksModify(WorksDto dto, HttpServletRequest request) throws IllegalStateException, IOException {
		log.debug("=============================");
		log.debug("작품을 수정합니다...... {}", dto);
		log.debug("=============================");
		
		String sDirectory = request.getServletContext().getRealPath("/static/images/works");
		
		//List<Long> deletedImageIds = dto.getDeleteImageIds();
		
		artistService.updateWorks(sDirectory, dto);
		
		//log.debug("작품 수정 결과 >> {}" , result);
	}
}
