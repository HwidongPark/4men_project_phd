package com.itwill.fourmen.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.fourmen.domain.Faq;
import com.itwill.fourmen.domain.Notice;
import com.itwill.fourmen.domain.Post;
import com.itwill.fourmen.domain.Qna;
import com.itwill.fourmen.dto.post.FaqCreateDto;
import com.itwill.fourmen.dto.post.FaqListItemDto;
import com.itwill.fourmen.dto.post.NoticeCreateDto;
import com.itwill.fourmen.dto.post.NoticeListItemDto;
import com.itwill.fourmen.dto.post.PostCreateDto;
import com.itwill.fourmen.dto.post.PostListItemDto;
import com.itwill.fourmen.dto.post.QnaCreateDto;
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
	
	// freeeboard 처리 시작 //
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
	
	@GetMapping("/freeboard-create") //-> GET 방식의 "/forum/freeboard-create" 요청 주소를 처리하는 메서드.
	public void freeboard_create() {
		log.debug("GET_FREEBOARD - create()");
	}
	
	@PostMapping("/freeboard-create") //-> POST 방식의 "/forum/freeboard-create" 요청 주소를 처리하는 메서드.
	public String freeboard_create(PostCreateDto dto) { //-> String 타입 사용: 이 메서드가 클라이언트에게 리턴하는 값이 URL 경로를 나타내기 때문.
		log.debug("POST_FREEBOARD - create(dto={}", dto);
		
		// 서비스 계층의 메서드를 호출해서 새 포스트 작성 서비스를 수행.
		postService.create(dto);
		
		return "redirect:/forum/freeboard";
	}
	
	@GetMapping("/freeboard-detail")
	public void freeboard_detail(@RequestParam(name = "post_id") long id, Model model) {
	
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
	Post post = postService.detail(id);
	
	// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
	model.addAttribute("post", post);
	}
	
	@GetMapping("freeboard-detail/delete") //-> GET 방식의 "freeboard-detail/delete" 요청 주소를 처리하는 메서드.
	/*
	 * 누가.. freeboard-detail/delete 요청 주소를 보냈을까? -> 자바스크립트 파일에서 보냄.
	 * freeboard-detail.js 파일에서 location.href = `freeboard-detail/delete?post_id=${postId.value}`; 가 실행되어,
	 * localhost:8081/fourmen/forum/freeboard-detail/delete?post_id=(번호) 요청이 서버로 전달된 것이다!
	 */
	public String freeboard_delete(long post_id) {
		
		postService.delete(post_id);
		
		return "redirect:/forum/freeboard";
	}
	// freeboard 처리 끝 //
	
	// qnaboard 처리 시작 //
	@GetMapping("/qnaboard")
	public void qnaboard(Model qnaboardModel) {
		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
		// model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
		
		// qnaService의 메서드를 호출해서 포스트 목록을 만들고 뷰에 전달.
		List<QnaLIstItemDto> list = qnaService.read();
		qnaboardModel.addAttribute("qnaboard_posts", list); //-> 뷰에 전달되는 데이터.
		
		log.debug("문의게시판 리스트 사이즈 = {}", list.size());
		
		// 리턴 값이 없으면 요청 경로로 뷰(JSP)를 찾음.
		//-> /WEB-INF/views/forum/qnaboard.jsp
	}
	
	@GetMapping("/qnaboard-create") //-> GET 방식의 "/forum/qnaboard-create" 요청 주소를 처리하는 메서드.
	public void qnaboard_create() {
		log.debug("GET_QNABOARD - create()");
	}
	
	@PostMapping("/qnaboard-create") //-> POST 방식의 "/forum/qnaboard-create" 요청 주소를 처리하는 메서드.
	public String qnaboard_create(QnaCreateDto dto) { //-> String 타입 사용: 이 메서드가 클라이언트에게 리턴하는 값이 URL 경로를 나타내기 때문.
		log.debug("POST_QNABOARD - create(dto={}", dto);
		
		// 서비스 계층의 메서드를 호출해서 새 포스트 작성 서비스를 수행.
		qnaService.create(dto);
		
		return "redirect:/forum/qnaboard";
	}
	
	@GetMapping("/qnaboard-detail")
	public void qnaboard_detail(@RequestParam(name = "qna_id") long id, Model model) {
	
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
	Qna qna = qnaService.detail(id);
	
	// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
	model.addAttribute("qna", qna);
	}
	
	@GetMapping("qnaboard-detail/delete") //-> GET 방식의 "qnaboard-detail/delete" 요청 주소를 처리하는 메서드.
	/*
	 * 누가.. qnaboard-detail/delete 요청 주소를 보냈을까? -> 자바스크립트 파일에서 보냄.
	 * freeboard-detail.js 파일에서 location.href = `freeboard-detail/delete?post_id=${postId.value}`; 가 실행되어,
	 * localhost:8081/fourmen/forum/freeboard-detail/delete?post_id=(번호) 요청이 서버로 전달된 것이다!
	 */
	public String qnaboard_delete(long qna_id) {
		
		postService.delete(qna_id);
		
		return "redirect:/forum/qnaboard";
	}
	// qnaboard 처리 끝 //
	
	// faqboard 처리 시작 //
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
	
	@GetMapping("/faqboard-create") //-> GET 방식의 "/forum/faqboard-create" 요청 주소를 처리하는 메서드.
	public void faqboard_create() {
		log.debug("GET_FAQBOARD - create()");
	}
	
	@PostMapping("/faqboard-create") //-> POST 방식의 "/forum/faqboard-create" 요청 주소를 처리하는 메서드.
	public String faqboard_create(FaqCreateDto dto) { //-> String 타입 사용: 이 메서드가 클라이언트에게 리턴하는 값이 URL 경로를 나타내기 때문.
		log.debug("POST_FAQBOARD - create(dto={}", dto);
		
		// 서비스 계층의 메서드를 호출해서 새 포스트 작성 서비스를 수행.
		faqService.create(dto);
		
		return "redirect:/forum/faqboard";
	}
	
	@GetMapping("/faqboard-detail")
	public void faqboard_detail(@RequestParam(name = "faq_id") long id, Model model) {
	
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
	Faq faq = faqService.detail(id);
	
	// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
	model.addAttribute("faq", faq);
	}
	
	@GetMapping("faqboard-detail/delete") //-> GET 방식의 "faqboard-detail/delete" 요청 주소를 처리하는 메서드.
	/*
	 * 누가.. faqboard-detail/delete 요청 주소를 보냈을까? -> 자바스크립트 파일에서 보냄.
	 * faqboard-detail.js 파일에서 location.href = `faqboard-detail/delete?faq_id=${faqId.value}`; 가 실행되어,
	 * localhost:8081/fourmen/forum/faqboard-detail/delete?faq_id=(번호) 요청이 서버로 전달된 것이다!
	 */
	public String faqboard_delete(long faq_id) {
		
		postService.delete(faq_id);
		
		return "redirect:/forum/faqboard";
	}
	// faqboard 처리 끝 //
	
	// noticeboard 처리 시작 //
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
	
	@GetMapping("/noticeboard-create") //-> GET 방식의 "/forum/noticeboard-create" 요청 주소를 처리하는 메서드.
	public void noticeboard_create() {
		log.debug("GET_noticeBOARD - create()");
	}
	
	@PostMapping("/noticeboard-create") //-> POST 방식의 "/forum/noticeboard-create" 요청 주소를 처리하는 메서드.
	public String noitceboard_create(NoticeCreateDto dto) { //-> String 타입 사용: 이 메서드가 클라이언트에게 리턴하는 값이 URL 경로를 나타내기 때문.
		log.debug("POST_NOTICEBOARD - create(dto={}", dto);
		
		// 서비스 계층의 메서드를 호출해서 새 포스트 작성 서비스를 수행.
		noticeService.create(dto);
		
		return "redirect:/forum/noticeboard";
	}
	
	@GetMapping("/noticeboard-detail")
	public void noticeboard_detail(@RequestParam(name = "notice_id") long id, Model model) {
	
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
	Notice notice = noticeService.detail(id);
	
	// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
	model.addAttribute("notice", notice);
	}
	
	@GetMapping("noticeboard-detail/delete") //-> GET 방식의 "noticeboard-detail/delete" 요청 주소를 처리하는 메서드.
	/*
	 * 누가.. noticeboard-detail/delete 요청 주소를 보냈을까? -> 자바스크립트 파일에서 보냄.
	 * noticeboard-detail.js 파일에서 location.href = `noticeboard-detail/delete?faq_id=${noticeId.value}`; 가 실행되어,
	 * localhost:8081/fourmen/forum/noticeboard-detail/delete?notice_id=(번호) 요청이 서버로 전달된 것이다!
	 */
	public String noticeboard_delete(long notice_id) {
		
		noticeService.delete(notice_id);
		
		return "redirect:/forum/noticeboard";
	}
	// noticeboard 처리 끝 //
	
}