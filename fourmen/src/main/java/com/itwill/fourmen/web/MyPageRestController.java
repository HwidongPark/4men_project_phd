package com.itwill.fourmen.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.fourmen.domain.Message;
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
	public ResponseEntity<Integer> message(Message message) {
		
		log.debug("message(message={})", message);
				
		int result = myPageService.sendMessage(message);
		log.debug("메세지 보내기 결과 = {}", result);
		
		return ResponseEntity.ok(result);
	}
	
	
}
