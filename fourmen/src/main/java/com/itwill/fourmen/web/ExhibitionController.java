package com.itwill.fourmen.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.exhibition.ExhibitionSearchDto;
import com.itwill.fourmen.service.ExhibitionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller // 컨트롤러 컴포넌트
public class ExhibitionController {
	
	private final ExhibitionService exhibitionService;
	
	@GetMapping("/exhibitionSearch")
	public String search(ExhibitionSearchDto dto, Model model) {
		log.debug("search(결과={})",dto);
		
		List<Exhibition> exhibition = exhibitionService.search(dto);
		
		model.addAttribute("exhibition",exhibition);
		
		return "/exhibition";
	}
}
