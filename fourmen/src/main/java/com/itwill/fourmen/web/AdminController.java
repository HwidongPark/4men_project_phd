package com.itwill.fourmen.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

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

import com.itwill.fourmen.board.PageMaker;
import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.domain.WishList;
import com.itwill.fourmen.dto.admin.UserUpdateDto;
import com.itwill.fourmen.dto.admin.exhibitioncreateDto;
import com.itwill.fourmen.dto.artist.ArtistUpdateDto;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.market.MarketSearchDto;
import com.itwill.fourmen.dto.market.PagingDto;
import com.itwill.fourmen.repository.AdminUserDao;
import com.itwill.fourmen.service.adminUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	 
	 @GetMapping("/marketadmin")
		public void market(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model, HttpServletRequest request, HttpSession session) {
			log.debug("market(page={})", page);
			
			int numOfPopularMarketPosts = 0;	// 인기글 개수 초기화.. 최대 8개로 제한할거임
			
			// 해당 페이지의 포스트들만 가져옴
			List<MarketPostDto> pagedMarketPosts = adminuserservice.readPagedMarketPosts(page);
			log.debug("pagedMarketPosts={}", pagedMarketPosts);
			
			PagingDto pagingDto = adminuserservice.paging(page);	// 페이지처리할 dto받아옴
			log.debug("pagingDto={}", pagingDto);
			
			int totNumPosts = adminuserservice.countTotNumber();	// 전체 포스트 개수 가져옴
			// 인기글 8개 읽어옴.. 예는 전체 포스트에서 가져온거 사용,,
			if (totNumPosts < 8) {
				numOfPopularMarketPosts = totNumPosts;
			} else {
				numOfPopularMarketPosts = 8;
			}
			List<MarketPostDto> popularMarketPosts = adminuserservice.readPopularMarketPosts(numOfPopularMarketPosts);	// 인기포스트 가져옴(서비스호출)
			log.debug("popularMarketPosts={}", popularMarketPosts);
			
			String sDirectory = request.getServletContext().getRealPath("/static/uploads");
			log.debug("sDirectory={}", sDirectory);
			
			String servletPath = request.getServletPath();
			log.debug("servletPath={}", servletPath);
			
			// 로그인한 상태라면 좋아요한 게시글 목록 읽어옴
			String signedInUser = (String) session.getAttribute("signedInUser");		
			log.debug("마켓리스트갈 때 로그인된 유저={}", signedInUser);
			if (signedInUser != null) {
				List<WishList> userWishList = adminuserservice.readWishList(signedInUser);
				log.debug("userWishList={}", userWishList);
				model.addAttribute("userWishList", userWishList);
			}
			// market의 게시글 리스트를 뷰로 전달
			// TODO:  변수이름 다시 생각.. 그냥 가도 되기는 함.
			model.addAttribute("marketPosts", pagedMarketPosts);	// 최신글들을 전달..
			model.addAttribute("pagingDto", pagingDto);	// 아래 페이징 처리할 dto전달
			// TODO: 인기글 8개 전달
			model.addAttribute("page", page);
			model.addAttribute("popularMarketPosts", popularMarketPosts);
			model.addAttribute("sDirectory", sDirectory);
			model.addAttribute("fileSeparator", File.separator);
			model.addAttribute("servletPath", servletPath);
			
					
	 }
	 
	 @GetMapping("/marketadmin/delete")
	 public String Marketdelete(@RequestParam(name="title") String title) {
		 log.debug("title={}",title);
		 adminuserservice.Marketdelete(title);
		 
		 return "redirect:/marketadmin";   
		    }
	 


@GetMapping("/marketadmin/search")
public String search(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model, MarketSearchDto dto,HttpServletRequest request) {
	log.debug("search(dto={})", dto);
	dto.setPage(page);
	log.debug("page={}", page);
	log.debug("search(dto={})", dto);	// TODO: 페이지 처리 저절로 입혀지나 안되나 실험
	
	List<MarketPostDto> pagedMarketPosts = adminuserservice.pagedSearchPosts(dto);
	log.debug("pagedSearchPosts={}", pagedMarketPosts);
	
	PagingDto pagingDto = adminuserservice.searchPaging(page, dto);	// 페이지처리할 dto받아옴
	log.debug("pagingDto={}", pagingDto);
	
	
	String sDirectory = request.getServletContext().getRealPath("/static/uploads");
	String servletPath = request.getServletPath();
	
	model.addAttribute("marketPosts", pagedMarketPosts);
	model.addAttribute("sDirectory", sDirectory);
	model.addAttribute("fileSeparator", File.separator);
	model.addAttribute("pagingDto", pagingDto);
	model.addAttribute("servletPath", servletPath);
	model.addAttribute("pageTitle", "검색 결과");
	
	
	return "/marketadmin/searchlist";
}

@GetMapping("/marketadmin/search/delete")
public String Marketsearchdelete(@RequestParam(name="title") String title) {
	 log.debug("title={}",title);
	 adminuserservice.Marketdelete(title);
	 
	 return "redirect:/marketadmin";   
	    }

@GetMapping("/forumadmin")
public void forumadmin() {
	
	
}
}