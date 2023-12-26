package com.itwill.fourmen.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(name = "myPage", path = {"/mypage"})
public class MyPageController {
	
	@GetMapping("/wishlist")
	public void wishList(Long userId) {
		log.debug("wishList(userId={})", userId);
		
		
	}
	
}
