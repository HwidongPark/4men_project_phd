package com.itwill.fourmen.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.domain.WishList;
import com.itwill.fourmen.dto.artist.ArtistDto;
import com.itwill.fourmen.dto.market.MarketCreateDto;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.market.MarketSearchDto;
import com.itwill.fourmen.dto.market.PagingDto;
import com.itwill.fourmen.service.ArtistService;
import com.itwill.fourmen.service.MarketService;
import com.itwill.fourmen.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.GetDelegate;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/market")
public class MarketController {
	
	private final MarketService marketService;
	private final UserService userService;
	private final ArtistService artistService;
	
	/**
	 * 마켓 메인페이지로 이동하는 컨트롤러 메서드.. 페이징 처리함
	 * @param model
	 * @param request
	 */
	@GetMapping("")
	public void market(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model, HttpServletRequest request, HttpSession session) {
		log.debug("market(page={})", page);
		
		int numOfPopularMarketPosts = 0;	// 인기글 개수 초기화.. 최대 8개로 제한할거임
		
		// 해당 페이지의 포스트들만 가져옴
		List<MarketPostDto> pagedMarketPosts = marketService.readPagedMarketPosts(page);
		log.debug("pagedMarketPosts={}", pagedMarketPosts);
		
		PagingDto pagingDto = marketService.paging(page);	// 페이지처리할 dto받아옴
		log.debug("pagingDto={}", pagingDto);
		
//		int totNumPosts = marketService.countTotNumber();	// 전체 포스트 개수 가져옴
//		// 인기글 8개 읽어옴.. 예는 전체 포스트에서 가져온거 사용,,
//		if (totNumPosts < 8) {
//			numOfPopularMarketPosts = totNumPosts;
//		} else {
//			numOfPopularMarketPosts = 8;
//		}
		
		// 인기글 전체 개수 가져옴
		marketService.getTotPopularNum();
		
		int totPopularPosts = marketService.getTotPopularNum();
		if (totPopularPosts < 8) {
			numOfPopularMarketPosts = totPopularPosts;			
		} else {
			numOfPopularMarketPosts = 8;
		}
		
		
		
		
		List<MarketPostDto> popularMarketPosts = marketService.readPopularMarketPosts(numOfPopularMarketPosts);	// 인기포스트 가져옴(서비스호출)
		log.debug("popularMarketPosts={}", popularMarketPosts);
		
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		log.debug("sDirectory={}", sDirectory);
		
		String servletPath = request.getServletPath();
		log.debug("servletPath={}", servletPath);
		
		// 로그인한 상태라면 좋아요한 게시글 목록 읽어옴
		String signedInUser = (String) session.getAttribute("signedInUser");
		User user = null;
		log.debug("마켓리스트갈 때 로그인된 유저={}", signedInUser);
		if (signedInUser != null) {
			List<WishList> userWishList = marketService.readWishList(signedInUser);
			log.debug("userWishList={}", userWishList);
			model.addAttribute("userWishList", userWishList);
			
			// 로그인한 상태라면 로그인한 유저 객체 읽어옴
			user = userService.selectUserById(signedInUser);
			log.debug("user={}", user);
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
		// 로그인된 유저가 있다면 
		model.addAttribute("signedInUser", user);
		
		
		
	}
	
	
	/**
	 * 마켓 게시글 상세보기 컨트롤러 메서드
	 * @param workid
	 * @param model
	 */
	@GetMapping("/detail")
	public void detail(@RequestParam Long workid, Model model, HttpSession session) {
		log.debug("detail(workId={}) GET", workid);			
		
		// 해당 게시물 읽어옴
		MarketPostDto marketPost = marketService.readMarketPost(workid);
		log.debug("marketPostDto = {}", marketPost);
		
		// 마켓 포스트 올린 유저의 아티스트 정보 가져옴
		ArtistDto artistDto = artistService.getArtist(marketPost.getUserId());
		
		// 조회수 추가
		marketService.addView(workid);
		
		// 로그인된 유저가 종아요 눌렀는지 여부 확인
		String signedInUser = (String) session.getAttribute("signedInUser");
		WishList wishList = new WishList();
		wishList.setUserId(signedInUser);
		wishList.setWorkId(workid);
		
		int isWishListed = 0;
		
		if (signedInUser != null) {
			isWishListed = marketService.readWishList(wishList);
			log.debug("로그인한 유저가 좋아요 눌렀나?={}", isWishListed);
		}
		
		
		model.addAttribute("artistDto", artistDto);
		model.addAttribute("signedInUser", signedInUser);
		model.addAttribute("marketPost", marketPost);
		model.addAttribute("isWishListed", isWishListed);
	}
	
	
	/**
	 * 마켓 글 작성 컨트롤러 메서드
	 */
	@GetMapping("/create")
	public void create() {
		log.debug("create() GET");
	}
	
	@PostMapping("/create")
	public String create(MarketCreateDto dto, HttpServletRequest request) throws IllegalStateException, IOException {
		log.debug("create(dto={}) POST", dto);
		
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		log.debug("sDirectory={}", sDirectory);
		
		marketService.createMarketPost(dto, sDirectory);
		
		return "redirect:/market";	// TODO: 일단 이렇게하고 서비스에서 파일전송되는지 확인해보자.
	}
	
	
	/**
	 * 마켓 최신글 게시판으로 보내는 컨트롤러 메서드
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/recent")
	public String marketRecentList(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model, HttpServletRequest request, HttpSession session) {
		
		// 해당 페이지의 포스트들만 가져옴
		List<MarketPostDto> pagedMarketPosts = marketService.readPagedMarketPosts(page);
		log.debug("pagedMarketPosts={}", pagedMarketPosts);
		
		PagingDto pagingDto = marketService.paging(page);	// 페이지처리할 dto받아옴
		log.debug("pagingDto={}", pagingDto);
		
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		log.debug("sDirectory={}", sDirectory);
		
		// recent게시판의 요청주소를 저장
		String servletPath = request.getServletPath();
		
		
		// 로그인한 상태라면 좋아요한 게시글 목록 읽어옴
		String signedInUser = (String) session.getAttribute("signedInUser");
		log.debug("최신리스트 갈 때 로그인된 유저={}", signedInUser);
		
		if (signedInUser != null) {
			List<WishList> userWishList = marketService.readWishList(signedInUser);
			log.debug("userWishList={}", userWishList);
			model.addAttribute("userWishList", userWishList);
		}
		
		
		
		// market의 게시글 리스트를 뷰로 전달
		model.addAttribute("marketPosts", pagedMarketPosts);
		model.addAttribute("sDirectory", sDirectory);
		model.addAttribute("fileSeparator", File.separator);
		model.addAttribute("pageTitle", "Recent Items");
		model.addAttribute("pagingDto", pagingDto);
		model.addAttribute("servletPath", servletPath);
		model.addAttribute("page", page);
		
		return "/market/list";
	}
	
	/**
	 * 마켓 인기글 게시판으로 보내는 컨트롤러 메서드.
	 * 최대 20개의 게시물만 보여주도록 해놓음
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/popular")
	public String marketPopularList(Model model, HttpServletRequest request, HttpSession session) {
		// TODO: 서비스, DAO만들기.. 밥먹고나서
		List<MarketPostDto> marketPosts = marketService.readPopularMarketPosts();
		log.debug("marketPopularList(marketPosts={})", marketPosts);
		
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		log.debug("sDirectory={}", sDirectory);
		
		
		// 로그인한 상태라면 좋아요한 게시글 목록 읽어옴
		String signedInUser = (String) session.getAttribute("signedInUser");
		log.debug("최신리스트 갈 때 로그인된 유저={}", signedInUser);
		
		if (signedInUser != null) {
			List<WishList> userWishList = marketService.readWishList(signedInUser);
			log.debug("userWishList={}", userWishList);
			model.addAttribute("userWishList", userWishList);
		}
		
		
		model.addAttribute("marketPosts", marketPosts);
		model.addAttribute("sDirectory", sDirectory);
		model.addAttribute("fileSeparator", File.separator);
		model.addAttribute("pageTitle", "Hot Items");
		
		return "/market/list";
	}
	
	
	@GetMapping("/search")
	public String search(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model, MarketSearchDto dto,HttpServletRequest request, HttpSession session) {
		log.debug("search(dto={})", dto);
		dto.setPage(page);
		log.debug("page={}", page);
		log.debug("search(dto={})", dto);	// TODO: 페이지 처리 저절로 입혀지나 안되나 실험
		
		List<MarketPostDto> pagedMarketPosts = marketService.pagedSearchPosts(dto);
		log.debug("pagedSearchPosts={}", pagedMarketPosts);
		
		PagingDto pagingDto = marketService.searchPaging(page, dto);	// 페이지처리할 dto받아옴
		log.debug("pagingDto={}", pagingDto);
		
		
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		String servletPath = request.getServletPath();
		
		// 로그인한 상태라면 좋아요한 게시글 목록 읽어옴
		String signedInUser = (String) session.getAttribute("signedInUser");
		log.debug("최신리스트 갈 때 로그인된 유저={}", signedInUser);
		
		if (signedInUser != null) {
			List<WishList> userWishList = marketService.readWishList(signedInUser);
			log.debug("userWishList={}", userWishList);
			model.addAttribute("userWishList", userWishList);
		}
		
		
		model.addAttribute("page", page);
		model.addAttribute("marketPosts", pagedMarketPosts);
		model.addAttribute("sDirectory", sDirectory);
		model.addAttribute("fileSeparator", File.separator);
		model.addAttribute("pagingDto", pagingDto);
		model.addAttribute("servletPath", servletPath);
		model.addAttribute("pageTitle", "Search Results");
		
		
		return "/market/list";
	}
	
	
	/**
	 * 게시글 삭제하는 컨트롤러 메서드.
	 * 데이터베이스에서 행 삭제 및 로컬 이미지파일 삭제함
	 * @param workid
	 * @param request
	 * @return
	 */
	
	@PostMapping("/delete")
	public String delete(@RequestParam(name = "workid") Long workid, HttpServletRequest request) {
		log.debug("delete(workid={})", workid);
		
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		
		int result = marketService.deleteMarketPost(workid, sDirectory);
		log.debug("컨트롤러에서 삭제결과={}", result);
		
		return "redirect:/market";
		
	}
	
	// 이건 그냥 업데이트 페이지를 띄워주는 컨트롤러 메서드
	@PostMapping("/update")
	public void update(Long workid, Model model) {
		log.debug("update(workId={}) POST", workid);
		MarketPostDto marketPost = marketService.readMarketPost(workid);
		log.debug("marketPostDto = {}", marketPost);
		
		String[] size = marketPost.getPaintingSize().split(" x ");
		String width = size[0];
		String height = size[1];
		String depth = size[2].split(" cm")[0];
				
		model.addAttribute("marketPost", marketPost);
		model.addAttribute("width", width);
		model.addAttribute("height", height);
		model.addAttribute("depth", depth);
		
	}
	
	
	// 이건 업데이트를 수행하는 컨트롤러 메서드
	@PostMapping("/modify")
	public String modify(MarketCreateDto dto, HttpServletRequest request) throws IllegalStateException, IOException {
		log.debug("modify(MarketCreateDto={})", dto);
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		int result = marketService.updateMarketPost(dto, sDirectory);
		log.debug("업데이트 결과={}", result);
		
		return String.format("redirect:/market/detail?workid=%d", dto.getWorkId());
	}
	
	
}    // MarketController 클래스 끝
