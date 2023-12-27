package com.itwill.fourmen.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.fourmen.domain.WishList;
import com.itwill.fourmen.domain.WorkImage;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.market.MarketPostRestDto;
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
	
	
	/**
	 * 로그인된 유저가 보고있는 마켓 게시물을 위시리스트에 추가.
	 * 만약 이미 추가됐을 경우 추가하지 않고 0을 반환
	 * @param wishList
	 * @return
	 */
	@PostMapping("/wishlist")
	public ResponseEntity<Integer> wishList(@RequestBody WishList wishList) {
		log.debug("wishList(wishList={})", wishList);
				
		int result = marketService.addWishList(wishList);
		log.debug("결과={}", result);
		
		return ResponseEntity.ok(result);
	}
	
	
}
