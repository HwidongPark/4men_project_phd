package com.itwill.fourmen.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.dto.MarketCreateDto;
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
	public void market() {
		log.debug("market() GET");
		
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
		
		String sDirectory = request.getServletContext().getRealPath("/uploads");
		log.debug("sDirectory={}", sDirectory);
		
		marketService.createMarketPost(dto, sDirectory);
		
		return "redirect:/";	// TODO: 일단 이렇게하고 서비스에서 파일전송되는지 확인해보자.
	}

}
