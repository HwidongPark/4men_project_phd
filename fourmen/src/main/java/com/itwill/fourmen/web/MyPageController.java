package com.itwill.fourmen.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.fourmen.domain.WishList;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.service.MarketService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(name = "myPage", path = {"/mypage"})
public class MyPageController {
	
	private final MarketService marketService;
	
	@GetMapping("/wishlist")
	public void wishList(HttpSession session, Model model) {
		String signedInUser = (String) session.getAttribute("signedInUser");
		
		log.debug("wishList()");
		log.debug("signedInUser={}", signedInUser);
		
		List<WishList> userWishList = marketService.readWishList(signedInUser);
		
		List<MarketPostDto> marketPostDtoList = new ArrayList<>();
		for (WishList wishListPost : userWishList) {
			marketPostDtoList.add(marketService.readMarketPost(wishListPost.getWorkId()));
		}
		
		// 유저가 찜한 게시물 목록들 log확인
		log.debug("marketPostDtoList={}", marketPostDtoList);		
				
		model.addAttribute("wishListPosts", marketPostDtoList);
		
	}
	
}
