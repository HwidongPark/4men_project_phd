package com.itwill.fourmen.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.dto.MarketCreateDto;
import com.itwill.fourmen.board.PageMaker;
import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.dto.admin.UserUpdateDto;
import com.itwill.fourmen.dto.admin.exhibitioncreateDto;
import com.itwill.fourmen.dto.artist.ArtistUpdateDto;
import com.itwill.fourmen.repository.AdminUserDao;
import com.itwill.fourmen.service.adminUserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final adminUserService adminuserservice;
	
	@GetMapping("/admin/detail")
	public void detail(@RequestParam(name="userid") String userid, Model model) {
		 User user = adminuserservice.selectById(userid);
		
		 model.addAttribute("user", user);
	}
	
	@PostMapping("/admin/update")
	public String update(@RequestParam(name="userid") String userid, UserUpdateDto dto) throws UnsupportedEncodingException {
		log.debug("update(userid={}, dto={})", userid, dto);
		
//		dto.setUserid(userid);
		adminuserservice.userUpdate(dto);
		userid = URLEncoder.encode(userid,"utf-8");
		return "redirect:/admin/detail?userid=" + userid;
	}
	
	@GetMapping("/admin/delete")
	public String delete(@RequestParam(name="userid") String userid) {
		
		adminuserservice.userdelete(userid);
		
		return "redirect:/admin";
	}
	
	
	 @RequestMapping(value = "/exhibitionadmin", method = RequestMethod.GET)
		public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		
			
			model.addAttribute("exhibition", adminuserservice.Exhibitonadmin(scri));
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(scri);
			pageMaker.setTotalCount(adminuserservice.ExhibitionadminlistCount(scri));
			
			model.addAttribute("pageMaker", pageMaker);
			
			return "/exhibitionadmin";
			
		}
	 
	 @GetMapping("/exhibitionadmin/delete")
	 public String Exhibitiondelete(@RequestParam(name="name") String name) {
		 adminuserservice.Exhibitiondelete(name);
		 
		 return "redirect:/exhibitionadmin";
	 }
	 
	 @GetMapping("/exhibitionadmin/plus")
	 public void Exhibitionplus() {
		 
	 };
	 
	 @PostMapping("/exhibitionadmin/plus/update")
	 public String Exhibitionplus(@ModelAttribute exhibitioncreateDto dto, HttpServletRequest request) throws IllegalStateException, IOException {
		 String sDirectory = request.getServletContext().getRealPath("/static/image");
		 
		 adminuserservice.upload(dto,sDirectory);
		 return "redirect:/exhibitionadmin";
	 };
	 	
}
