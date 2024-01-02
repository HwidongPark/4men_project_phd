package com.itwill.fourmen.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.fourmen.domain.Message;
import com.itwill.fourmen.dto.mymessage.MyMessageDto;
import com.itwill.fourmen.service.MarketService;
import com.itwill.fourmen.service.MyPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(name = "myPage", path = {"/mypage"})
@RestController
public class MyPageRestController {
	private final MyPageService myPageService;
	
	@PostMapping("/message")
	public ResponseEntity<Integer> message(@RequestBody Message message) {
		
		log.debug("message(message={})", message);
				
		int result = myPageService.sendMessage(message);
		log.debug("메세지 보내기 결과 = {}", result);
		
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping("/mymessage/confirm-deal")
	public ResponseEntity<Integer> confirmDeal(@RequestBody Message message) {
		
		log.debug("confirmDeal(message={})", message);
		
		int result = myPageService.confirmDeal(message);
		log.debug("거래 확정 결과={}", result);
		
		return ResponseEntity.ok(result);
	}
	
	
}
