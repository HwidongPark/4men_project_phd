package com.itwill.fourmen.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.fourmen.domain.Faq;
import com.itwill.fourmen.domain.Notice;
import com.itwill.fourmen.domain.Post;
import com.itwill.fourmen.domain.Qna;
import com.itwill.fourmen.dto.post.FaqListItemDto;
import com.itwill.fourmen.dto.post.NoticeListItemDto;
import com.itwill.fourmen.dto.post.PostListItemDto;
import com.itwill.fourmen.dto.post.QnaLIstItemDto;
import com.itwill.fourmen.service.FaqService;
import com.itwill.fourmen.service.NoticeService;
import com.itwill.fourmen.service.PostService;
import com.itwill.fourmen.service.QnaService;

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
	
	private final QnaService qnaService; // 객체 생성 권한이 Spring FrameWork로 이전됨. (의존성 주입, 제어의 역전)
	
	private final FaqService faqService; // 객체 생성 권한이 Spring FrameWork로 이전됨. (의존성 주입, 제어의 역전)
	
	private final NoticeService noticeService; // 객체 생성 권한이 Spring FrameWork로 이전됨. (의존성 주입, 제어의 역전)
	
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
		
		// qnaService의 메서드를 호출해서 포스트 목록을 만들고 뷰에 전달.
		List<QnaLIstItemDto> list = qnaService.read();
		queryboardModel.addAttribute("queryboard_posts", list); //-> 뷰에 전달되는 데이터.
		
		log.debug("문의게시판 리스트 사이즈 = {}", list.size());
		
		// 리턴 값이 없으면 요청 경로로 뷰(JSP)를 찾음.
		//-> /WEB-INF/views/forum/queryboard.jsp
	}
	
	@GetMapping("/faqboard")
	public void FAQboard(Model faqboardModel) {
		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
		// model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
		
		// faqService의 메서드를 호출해서 포스트 목록을 만들고 뷰에 전달.
		List<FaqListItemDto> list = faqService.read();
		faqboardModel.addAttribute("faqboard_posts", list); //-> 뷰에 전달되는 데이터.
		
		log.debug("Faq게시판 리스트 사이즈 = {}", list.size());
		
		// 리턴 값이 없으면 요청 경로로 뷰(JSP)를 찾음.
		//-> /WEB-INF/views/forum/faqboard.jsp
	}
	
	@GetMapping("/noticeboard")
		public void notice(Model noticeboardModel) {
		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
		// model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
		
		// noticeService의 메서드를 호출해서 포스트 목록을 만들고 뷰에 전달.
		List<NoticeListItemDto> list = noticeService.read();
		noticeboardModel.addAttribute("noticeboard_posts", list); //-> 뷰에 전달되는 데이터.
		
		log.debug("공지게시판 리스트 사이즈 = {}", list.size());
		
		// 리턴 값이 없으면 요청 경로로 뷰(JSP)를 찾음.
		//-> /WEB-INF/views/forum/noticeboard.jsp
	}
	
	@GetMapping("/freeboard-detail")
		public void freeboard_detail(@RequestParam(name = "post_id") long id, Model model) {
		
		// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
		Post post = postService.detail(id);
		
		// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
		model.addAttribute("post", post);
	}
	
	@GetMapping("/queryboard-detail")
		public void queryboard_detail(@RequestParam(name = "qna_id") long id, Model model) {
			
		// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
		Qna qna = qnaService.detail(id);
		
		// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
		model.addAttribute("qna", qna);
	}
		
	@GetMapping("/faqboard-detail")
		public void faqboard_detail(@RequestParam(name = "faq_id") long id, Model model) {
			
		// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
		Faq faq = faqService.detail(id);
		
		// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
		model.addAttribute("faq", faq);
	}
		
	@GetMapping("/noticeboard-detail")
		public void noticeboard_detail(@RequestParam(name = "notice_id") long id, Model model) {
			
		// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
		Notice notice = noticeService.detail(id);
		
		// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
		model.addAttribute("notice", notice);
	
	}
	
}