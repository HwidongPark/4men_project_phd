package com.itwill.fourmen.web;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.dto.artist.ArtistWorksListItemDto;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.market.PagingDto;
import com.itwill.fourmen.dto.myinfo.MyInfoUpdateDto;
import com.itwill.fourmen.repository.MyPageInfoDao;
import com.itwill.fourmen.service.ArtistService;
import com.itwill.fourmen.service.MarketService;
import com.itwill.fourmen.service.MyPageInfoService;
import com.itwill.fourmen.service.UserService;
import com.itwill.fourmen.service.adminUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class MyPageMyInfoController {
	
	private final ArtistService artistService;
	private final UserService userService;
	private final MyPageInfoService myPageInfoService;
	private final MarketService marketService;
	private final adminUserService adminUserService;
	
	/**
	 * myInfo페이지에 내 정보를 띄워줌.
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/myinfo")
	public String myInfo(HttpSession session, Model model) {
		String signedInUser = (String) session.getAttribute("signedInUser");
		log.debug("myInfo(signedInUser={})", signedInUser);
		
		User user = userService.selectUserById(signedInUser);
		log.debug("user={}", user);
		
		model.addAttribute("user", user);
		
		return "/mypage/myinfo";
	}
	
	
	/**
	 * 유저가 업데이트할 정보들을 받아서 업데이트하는 컨트롤러 메서드
	 * @param dto
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("/myinfo/update")
	public String updateuser(MyInfoUpdateDto dto, HttpServletRequest request) throws IllegalStateException, IOException {
		log.debug("updateUser(dto={})", dto);
		
		String sDirectory = request.getServletContext().getRealPath("/static/image");
		log.debug("sDirectory={}", sDirectory);
		
		int result = userService.updateUser(dto, sDirectory);
		log.debug("업데이트 결과 = {}", result);
		
		return "redirect:/mypage/myinfo";
	}
	
	/**
	 * 로그인한 유저의 계정을 삭제하는 컨트롤러 메서드
	 * @param session
	 * @return
	 */
	@GetMapping("/myinfo/delete")
	public String deleteUser(HttpSession session) {
		String signedInUser = (String) session.getAttribute("signedInUser");		
		log.debug("deleteuser(signedInUser={})", signedInUser);
		
		int result = adminUserService.userdelete(signedInUser);
		log.debug("유저 삭제 결과 = {}", result);
		
		if (result == 1) {
			session.removeAttribute("signedInUser");
			session.removeAttribute("grade");
		}
		
		return "redirect:/";
	}
	
	
	
	/**
	 * 로그인 유저가 아티스트로서 만든 작품 리스트들을 보여주는 controller
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/mywork")
	public String myWorks(HttpSession session, Model model) {
		String signedInUser = (String) session.getAttribute("signedInUser");
		log.debug("myWorks(signedInUser={})", signedInUser);
		
		List<ArtistWorksListItemDto> worksList = artistService.readWorks(signedInUser);
		model.addAttribute("worksList", worksList);
		
		return "/mypage/mywork";
	}
	
	
	/**
	 * 내 마켓 작성글들을 전부 가져와서 보여주는 컨트롤러 메서드
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/mymarket")
	public String myMarket(HttpSession session, Model model, HttpServletRequest request
			, @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		String signedInUser = (String) session.getAttribute("signedInUser");
		log.debug("myMarket(signedInUser={})", signedInUser);
		
		// 현재 페이지에 내가 작성한 marketPostsDto 리스트를 가져옴
		List<MarketPostDto> marketPosts = marketService.readMyMarketPosts(signedInUser, page);
		log.debug("marketPosts={}", marketPosts);
		
		// 요청주소 view에 넘겨줌
		String servletPath = request.getServletPath();
		log.debug("servletPath={}", servletPath);
		
		// PagingDto가져옴
		PagingDto pagingDto = myPageInfoService.paging(page, signedInUser);
		log.debug("pagingDto={}", pagingDto);
		
		model.addAttribute("page", page);
		model.addAttribute("pagingDto", pagingDto);
		model.addAttribute("servletPath", servletPath);
		model.addAttribute("marketPosts", marketPosts);
		
		return "/mypage/mymarket";
	}
	
	
	
	
}
