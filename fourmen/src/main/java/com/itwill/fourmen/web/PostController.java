package com.itwill.fourmen.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.fourmen.dto.post.PostListItemDto;
import com.itwill.fourmen.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor //-> final 필드들을 초기화하는 아규먼트를 갖는 생성자.
@Controller
@RequestMapping("/forum")
//-> PostController의 컨트롤러 메서드의 매핑 URL(주소)은 "/forum"으로 시작.
public class PostController {
	
	// 생성자에 의한 의존성 주입. @RequiredArgsConstructor 사용.
	private final PostService postService; // 객체 생성 권한이 Spring FrameWork로 이전됨. (의존성 주입, 제어의 역전)
	// Spring Container가 가지고 있는 빈(bean = 객체) 중에서 타입에 맞는 객체를 주입해줌.
	
	@GetMapping("/freeboard") //-> GET 방식의 "/forum/freeboard" 요청 주소를 처리하는 메서드.
	public void freeboard(Model freeboardModel) {
		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
		// Model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
		
		// postService의 메서드를 호출해서 포스트 목록을 만들고 뷰에 전달.
		List<PostListItemDto> list = postService.read();
		freeboardModel.addAttribute("freeboard_posts", list); //-> 뷰에 전달되는 데이터.
		
		log.debug("자유게시판 리스트 사이즈 = {}", list.size());
		
		// 리턴 값이 없으면 요청 경로로 뷰(JSP)를 찾음.
		//-> /WEB-INF/views/forum/freeboard.jsp
	}
	
	@GetMapping("/queryboard")
	public void queryboard(Model queryboardModel) {
		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
		// model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
		log.debug("queryboard()");
	}
	
	@GetMapping("/faqboard")
	public void FAQboard(Model faqboardModel) {
		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
		// model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
		log.debug("faqboard()");
	}
	
	@GetMapping("/noticeboard") 
		public void notice(Model noticeboardModel) {
		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
		// model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
		log.debug("noticeboard()");
	}
	
	
	
}
