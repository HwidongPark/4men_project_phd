package com.itwill.fourmen.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.dto.MarketPostDto;
import com.itwill.dto.MarketPostRestDto;
import com.itwill.fourmen.domain.WorkImage;
import com.itwill.fourmen.service.MarketService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/market")
public class MarketRestController {
	
	private final MarketService marketService;
	
	@GetMapping("/fetch/{workId}")
	public ResponseEntity<MarketPostRestDto> fetchMarketPostObject(@PathVariable Long workId) {
		log.debug("fetch(workId={}) GET", workId);
		MarketPostRestDto marketPost = marketService.readMarketRestPost(workId);
		log.debug("marketPostDto = {}", marketPost);			
		
		return ResponseEntity.ok(marketPost);
	}
	
	
	/**
	 *  게시글 수정페이지에서 이미지 삭제할 때 호출하는 함수.
	 *  한번에 이미지 하나만 삭제함
	 */
	
	@DeleteMapping("/delete/image/{imgId}")
	public ResponseEntity<Integer> deleteImage(@PathVariable Long imgId, HttpServletRequest request) {
		log.debug("deleteImage(imgId={})", imgId);
		
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		log.debug("sDirectory={}", sDirectory);
		
		int result = marketService.deleteImage(imgId, sDirectory);
		log.debug("image delete result = {}", result);
		
		return ResponseEntity.ok(result);
	}
	
}
