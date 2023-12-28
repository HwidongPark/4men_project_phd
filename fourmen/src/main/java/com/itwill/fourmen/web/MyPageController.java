package com.itwill.fourmen.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.fourmen.domain.Message;
import com.itwill.fourmen.domain.WishList;
import com.itwill.fourmen.dto.market.MarketPostDto;
import com.itwill.fourmen.dto.mymessage.MyMessageDto;
import com.itwill.fourmen.service.MarketService;
import com.itwill.fourmen.service.MyPageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(name = "myPage", path = {"/mypage"})
public class MyPageController {
	
	private final MarketService marketService;
	private final MyPageService myPageService;
	
	/**
	 * 현재 로그인한 유저가 찜한 게시글들을 보여주는 컨트롤러
	 * @param session
	 * @param model
	 */
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
	
	
	/**
	 * 현재 로그인한 유저가 받은 메세지를 보여주는 컨트롤러
	 * @param session
	 * @return
	 */
	@GetMapping("/mymessage")
	public String myMessage(HttpSession session, HttpServletRequest request, Model model) {
		String signedInUser = (String) session.getAttribute("signedInUser");
		
		log.debug("myMessage(signedInUser={})", signedInUser);
		
		// 메세지 받은거 다 가져옴
		List<Message> myMessages = myPageService.readMyMessage(signedInUser);
		log.debug("myMessages={}", myMessages);
		
		List<MyMessageDto> messageDtoList = new ArrayList<>(); 
		
		// 해당 메세지와 관련된 포스트 정보 가져옴
		for (Message message : myMessages) {
			MarketPostDto postDto = marketService.readMarketPost(message.getWorkId());
			MyMessageDto messageDto = MyMessageDto.fromEntity(message, postDto);
			messageDtoList.add(messageDto);
		}
		
		log.debug("messageDtoList={}", messageDtoList);		
		
		String sDirectory = request.getServletContext().getRealPath("/static/uploads");
		
		model.addAttribute("myMessagesDtoList", messageDtoList);
		model.addAttribute("sDirectory", sDirectory);
		
		return "/mypage/mymessage";
	}
	
	
	/**
	 * 메세지의 id를 아규먼트로 받아 해당 메세지의 detail을 보여주는 controller메서드
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/mymessage/detail")
	public String myMessageDetail(Long id, Model model) {
		log.debug("myMessageDetail(id={})", id);
		
		MyMessageDto messageDto = myPageService.readMymessage(id);
		log.debug("messageDto={}", messageDto);
		
		model.addAttribute("messageDto", messageDto);
		
		return "/mypage/mymessage/detail";
	}
	
	
	/**
	 * 답장할 메세지의 id를 아규먼트로 받아 답장화면을 띄워주는 controller method
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/mymessage/reply")
	public String reply(Long id, Model model) {
		
		log.debug("reply(id={})", id);				
		
		// 답장할 상대방 메세지 정보 가져옴
		MyMessageDto messageDto = myPageService.readMymessage(id);
		log.debug("messageDto={}", messageDto);
		
		model.addAttribute("messageDto", messageDto);		
		
		return "/mypage/mymessage/reply";
	}
	
	
	
	@PostMapping("/mymessage/reply")
	public String reply (MyMessageDto messageDto, Model model) {
		
		log.debug("POST - reply(messageDto={})", messageDto);
		
		int result = myPageService.replyMessage(messageDto);
		log.debug("답장 결과={}", result);
		
		if (result == 1) {
			model.addAttribute("message", "답장을 성공적으로 보냈습니다.");
			model.addAttribute("willClose", "close");
			
		} else if (result == 0) {
			model.addAttribute("message", "답장 실패");
			model.addAttribute("willClose", "close");
		}
		
		
		return "/alert";
	}
}
