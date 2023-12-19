package com.itwill.fourmen.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.dto.MarketCreateDto;
import com.itwill.dto.MarketPostDto;
import com.itwill.fourmen.domain.Market;
import com.itwill.fourmen.service.MarketService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/market")
public class MarketController {
	
	private final MarketService marketService;	
	
	
	@GetMapping("")
	public void market(Model model, HttpServletRequest request) {
		List<MarketPostDto> marketPosts = marketService.readMarketPosts();
		log.debug("market(postLists={}) GET", marketPosts);
		
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		log.debug("sDirectory={}", sDirectory);
		log.debug("fileSeparator={}", File.separator);
		// market의 게시글 리스트를 뷰로 전달
		model.addAttribute("marketPosts", marketPosts);
		model.addAttribute("sDirectory", sDirectory);
		model.addAttribute("fileSeparator", File.separator);
		
		
	}
	
	@GetMapping("/detail")
	public void detail() {
		log.debug("detail() GET");
	}
	
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

}
