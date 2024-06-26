package com.itwill.fourmen.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.fourmen.board.PageMaker;
import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.board.SearchCriteriaAdminUser;
import com.itwill.fourmen.domain.Faq;
import com.itwill.fourmen.domain.Notice;
import com.itwill.fourmen.domain.Post;
import com.itwill.fourmen.domain.Qna;
import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.dto.market.PagingDto;
import com.itwill.fourmen.dto.post.FaqCreateDto;
import com.itwill.fourmen.dto.post.FaqListItemDto;
import com.itwill.fourmen.dto.post.FaqModifyDto;
import com.itwill.fourmen.dto.post.FaqSearchDto;
import com.itwill.fourmen.dto.post.NoticeCreateDto;
import com.itwill.fourmen.dto.post.NoticeListItemDto;
import com.itwill.fourmen.dto.post.NoticeModifyDto;
import com.itwill.fourmen.dto.post.NoticeSearchDto;
import com.itwill.fourmen.dto.post.PostCreateDto;
import com.itwill.fourmen.dto.post.PostListItemDto;
import com.itwill.fourmen.dto.post.PostModifyDto;
import com.itwill.fourmen.dto.post.PostSearchDto;
import com.itwill.fourmen.dto.post.QnaCreateDto;
import com.itwill.fourmen.dto.post.QnaLIstItemDto;
import com.itwill.fourmen.dto.post.QnaModifyDto;
import com.itwill.fourmen.dto.post.QnaSearchDto;
import com.itwill.fourmen.service.FaqService;
import com.itwill.fourmen.service.NoticeService;
import com.itwill.fourmen.service.PostService;
import com.itwill.fourmen.service.QnaService;
import com.itwill.fourmen.service.UserService;
import com.itwill.fourmen.service.adminUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
		
	private final adminUserService adminuserservice;
	
	// freeeboard 처리 시작 //
//	@GetMapping("/freeboard") //-> GET 방식의 "/forum/freeboard" 요청 주소를 처리하는 메서드.
//	public void freeboard(Model freeboardModel) {
//		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
//		// Model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
//		
//		// postService의 메서드를 호출해서 포스트 목록을 만들고 뷰에 전달.
//		List<PostListItemDto> list = postService.read();
//		freeboardModel.addAttribute("freeboard_posts", list); //-> 뷰에 전달되는 데이터.
//		
//		log.debug("자유게시판 리스트 사이즈 = {}", list.size());
//		
//		// 리턴 값이 없으면 요청 경로로 뷰(JSP)를 찾음.
//		//-> /WEB-INF/views/forum/freeboard.jsp
//	}
	
	@RequestMapping(value = "/freeboard", method = RequestMethod.GET)
	public String freeboard_list(Model model, @ModelAttribute("scri") SearchCriteriaAdminUser scri, @RequestParam(name = "page", required = false, defaultValue = "1") int page, HttpSession session) throws Exception {
		String signedInUser = (String) session.getAttribute("signedInUser");
	   	if(!(signedInUser==null)) {
	   		User user = adminuserservice.selectById(signedInUser);
	   		model.addAttribute("user", user);
	   	}
		
		model.addAttribute("freeboard_posts", postService.read(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(postService.listCount(scri));

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("page", page);

		return "/forum/freeboard";
	}
	
	@GetMapping("/freeboard-create") //-> GET 방식의 "/forum/freeboard-create" 요청 주소를 처리하는 메서드.
	public void freeboard_create(Model model, HttpSession session) {
		log.debug("GET_FREEBOARD - create()");
		String signedInUser = (String) session.getAttribute("signedInUser");
		model.addAttribute("signedInUser", signedInUser);
	}
	
	@PostMapping("/freeboard-create") //-> POST 방식의 "/forum/freeboard-create" 요청 주소를 처리하는 메서드.
	public String freeboard_create(PostCreateDto dto, HttpSession session, Model model) { //-> String 타입 사용: 이 메서드가 클라이언트에게 리턴하는 값이 URL 경로를 나타내기 때문.
		log.debug("POST_FREEBOARD - create(dto={}", dto);
		
		// 서비스 계층의 메서드를 호출해서 새 포스트 작성 서비스를 수행.
		postService.create(dto);
		
		return "redirect:/forum/freeboard";
	}
	
	@GetMapping({"/freeboard-detail", "/freeboard-modify"})
	//-> /freeboard-detail, /freeboard-modify 2개의 요청을 처리하는 메서드!
	public void freeboard_detail(@RequestParam(name = "post_id") long post_id, Model model, HttpSession session) {
	
	String signedInUser = (String) session.getAttribute("signedInUser");
   	if(!(signedInUser==null)) {
   		User user = adminuserservice.selectById(signedInUser);
   		model.addAttribute("user",user);
   	}
		
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
	Post post = postService.detail(post_id);
	
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 조회수 내용을 읽음.
	postService.addView(post_id);
	
	// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
	model.addAttribute("post", post);
	}
	
	@PostMapping("/freeboard-modify/{post_id}") //-> 게시글 업데이트를 위한 메서드...
	public ResponseEntity<Integer> freeboard_modify(@RequestBody PostModifyDto dto, @PathVariable long post_id) {
		log.debug("=====================================");
		log.debug("DTO = {}, POST_ID = {}", dto, post_id);
		log.debug("=====================================");
		
		int result = postService.update(dto, post_id);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping({"freeboard-detail/delete", "freeboard-modify/delete"}) //-> 게시글 삭제...
	/*
	 * 누가.. freeboard-detail/delete 요청 주소를 보냈을까? -> 자바스크립트 파일에서 보냄.
	 * freeboard-detail.js 파일에서 location.href = `freeboard-detail/delete?post_id=${postId.value}`; 가 실행되어,
	 * localhost:8081/fourmen/forum/freeboard-detail/delete?post_id=(번호) 요청이 서버로 전달된 것이다!
	 */
	public String freeboard_delete(long post_id) {
		
		postService.delete(post_id);
		
		return "redirect:/forum/freeboard";
	}
	
	@GetMapping("freeboard_search") //-> GET 방식의 "forum/freeboard_search" 요청 주소를 처리하는 메서드.
	public String search(PostSearchDto dto, Model model) {
    	log.debug("search(dto={})", dto);
    
		// 서비스 계층의 메서드를 호출해서 검색 서비스를 수행.
    	List<PostListItemDto> list = postService.search(dto);
    
    	// 검색 결과를 뷰에 전달하기 위해서 모델 속성(attribute)에 추가.
    	model.addAttribute("freeboard_posts", list);
    	
    	return "forum/freeboard"; //-> 뷰의 경로(/WEB-INF/views/forum/freeboard.jsp)
    	//-> servlet-context에서 prefix 부분이 /WEB-INF/views/ 였으므로.
	}
	// freeboard 처리 끝 //
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// qnaboard 처리 시작 //
//	@GetMapping("/qnaboard")
//	public void qnaboard(Model qnaboardModel) {
//		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
//		// model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
//		
//		// qnaService의 메서드를 호출해서 포스트 목록을 만들고 뷰에 전달.
//		List<QnaLIstItemDto> list = qnaService.read(scri);
//		qnaboardModel.addAttribute("qnaboard_posts", list); //-> 뷰에 전달되는 데이터.
//		
//		log.debug("문의게시판 리스트 사이즈 = {}", list.size());
//		
//		// 리턴 값이 없으면 요청 경로로 뷰(JSP)를 찾음.
//		//-> /WEB-INF/views/forum/qnaboard.jsp
//	}
	
//	@RequestMapping(value = "/qnaboard", method = RequestMethod.GET)
//	public String qnaboard_list(Model model, @ModelAttribute("scri") SearchCriteriaAdminUser scri, @RequestParam(name = "page", required = false, defaultValue = "1") int page, HttpSession session) throws Exception {
//		String signedInUser = (String) session.getAttribute("signedInUser");
//	   	if(!(signedInUser==null)) {
//	   		User user = adminuserservice.selectById(signedInUser);
//	   		model.addAttribute("user", user);
//	   	}
//		
//		model.addAttribute("qnaboard_posts", qnaService.read(scri));
//
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(scri);
//		pageMaker.setTotalCount(qnaService.listCount(scri));
//
//		model.addAttribute("pageMaker", pageMaker);
//		model.addAttribute("page", page);
//
//		return "/forum/qnaboard";
//	}
	
	
	@RequestMapping(value = "/qnaboard", method = RequestMethod.GET)
	public String qnaboard_list(Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page, HttpSession session) throws Exception {
		String signedInUser = (String) session.getAttribute("signedInUser");
	   	if(!(signedInUser==null)) {
	   		User user = adminuserservice.selectById(signedInUser);
	   		model.addAttribute("user", user);
	   	}
		
	   	
	   	List<Qna> qnaboard_posts = qnaService.read(page);
	   	log.debug("qna최신글 리스트 = {}", qnaboard_posts);
	   	model.addAttribute("qnaboard_posts", qnaboard_posts);
	   	
	   	
	   	// 전체 포스트 개수
	   	int totNumQnaPosts = qnaService.totQnaPosts();
	   	log.debug("전체 qna게시글 개수={}", totNumQnaPosts);
	   	PagingDto pagingDto = qnaService.paging(page, totNumQnaPosts);

		model.addAttribute("pagingDto", pagingDto);
		model.addAttribute("page", page);

		return "/forum/qnaboard";
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
	
	@GetMapping({"/qnaboard-detail", "/qnaboard-modify"})
	//-> /qnaboard-detail, /qnaboard-modify 2개의 요청을 처리하는 메서드!
	public void qnaboard_detail(@RequestParam(name = "qna_id") long qna_id, Model model, HttpSession session) {
	
	String signedInUser = (String) session.getAttribute("signedInUser");
   	if(!(signedInUser==null)) {
	   	User user = adminuserservice.selectById(signedInUser);
	   	model.addAttribute("user",user);
	}	

	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
	Qna qna = qnaService.detail(qna_id);
	
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 조회수 내용을 읽음.
	qnaService.addView(qna_id);
	
	// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
	model.addAttribute("qna", qna);
	}
	
	@PostMapping("/qnaboard-modify/{qna_id}") //-> 게시글 업데이트를 위한 메서드...
	public ResponseEntity<Integer> qnaboard_modify(@RequestBody QnaModifyDto dto, @PathVariable long qna_id) {
		log.debug("=====================================");
		log.debug("DTO = {}, QNA_ID = {}", dto, qna_id);
		log.debug("=====================================");
		
		int result = qnaService.update(dto, qna_id);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping({"qnaboard-detail/delete", "qnaboard-modify/delete"}) //-> 게시글 삭제...
	/*
	 * 누가.. qnaboard-detail/delete 요청 주소를 보냈을까? -> 자바스크립트 파일에서 보냄.
	 * freeboard-detail.js 파일에서 location.href = `freeboard-detail/delete?post_id=${postId.value}`; 가 실행되어,
	 * localhost:8081/fourmen/forum/freeboard-detail/delete?post_id=(번호) 요청이 서버로 전달된 것이다!
	 */
	public String qnaboard_delete(long qna_id) {
		
		qnaService.delete(qna_id);
		
		return "redirect:/forum/qnaboard";
	}
	
//	@GetMapping("qnaboard_search") //-> GET 방식의 "forum/qnaboard_search" 요청 주소를 처리하는 메서드.
//	public String search(QnaSearchDto dto, Model model, @ModelAttribute("scri") SearchCriteriaAdminUser scri, @RequestParam(name = "page", required = false, defaultValue = "1")int page
//			,HttpServletRequest request) {
//    	log.debug("search(dto={})", dto);
//    
//		// 서비스 계층의 메서드를 호출해서 검색 서비스를 수행.
//    	List<QnaLIstItemDto> list = qnaService.search(dto, page);
//    	int totNumSearchList = qnaService.searchTotNumber(dto);
//    	
//    	scri.setPerPageNum(9);
//    	scri.setPage(page);
//    	log.debug("scri, page = {}", scri.getPage());
//    	log.debug("scri, porPageNum= {}", scri.getPerPageNum());
//    	
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(scri);
//		pageMaker.setTotalCount(totNumSearchList);
//		
//		String servletPath = request.getServletPath();
//		
//		
//		model.addAttribute("pageMaker", pageMaker);
//		model.addAttribute("page", page);
//    	
//    	// 검색 결과를 뷰에 전달하기 위해서 모델 속성(attribute)에 추가.
//    	model.addAttribute("qnaboard_posts", list);
//    	model.addAttribute("pageMaker", pageMaker);
//    	model.addAttribute("page", page);
//    	model.addAttribute("servletPath", servletPath);
//    	
//    	log.debug("pageMaker={}", pageMaker);
//    	log.debug("scri={}", scri);
//    	log.debug("listSize={}", list.size());
//    
//    	
//    	return "forum/qnaboard"; //-> 뷰의 경로(/WEB-INF/views/forum/qnaboard.jsp)
//    	//-> servlet-context에서 prefix 부분이 /WEB-INF/views/ 였으므로.
//	}
	
	
	@GetMapping("qnaboard_search") //-> GET 방식의 "forum/qnaboard_search" 요청 주소를 처리하는 메서드.
	public String search(QnaSearchDto dto, Model model, @RequestParam(name = "page", required = false, defaultValue = "1")int page, HttpServletRequest request) {
    	log.debug("search(dto={})", dto);
    	
		// 서비스 계층의 메서드를 호출해서 검색 서비스를 수행.
    	List<QnaLIstItemDto> list = qnaService.search(dto, page);
    	int totNumSearchList = qnaService.searchTotNumber(dto);
		
		String servletPath = request.getServletPath();
		
		PagingDto pagingDto = qnaService.paging(page, totNumSearchList);
		
		// model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("page", page);
    	
    	// 검색 결과를 뷰에 전달하기 위해서 모델 속성(attribute)에 추가.
    	model.addAttribute("qnaboard_posts", list);
    	
    	
    	model.addAttribute("pagingDto", pagingDto);
    	model.addAttribute("page", page);
    	model.addAttribute("servletPath", servletPath);
    	
    	// log.debug("pageMaker={}", pageMaker);
    	log.debug("listSize={}", list.size());
    
    	
    	return "forum/qnaboard"; //-> 뷰의 경로(/WEB-INF/views/forum/qnaboard.jsp)
    	//-> servlet-context에서 prefix 부분이 /WEB-INF/views/ 였으므로.
	}
	
	
	
	// qnaboard 처리 끝 //
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// faqboard 처리 시작 //
//	@GetMapping("/faqboard")
//	public void FAQboard(Model faqboardModel) {
//		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
//		// model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
//		
//		// faqService의 메서드를 호출해서 포스트 목록을 만들고 뷰에 전달.
//		List<FaqListItemDto> list = faqService.read();
//		faqboardModel.addAttribute("faqboard_posts", list); //-> 뷰에 전달되는 데이터.
//		
//		log.debug("Faq게시판 리스트 사이즈 = {}", list.size());
//		
//		// 리턴 값이 없으면 요청 경로로 뷰(JSP)를 찾음.
//		//-> /WEB-INF/views/forum/faqboard.jsp
//	}
	
	@RequestMapping(value = "/faqboard", method = RequestMethod.GET)
	public String faqboard_list(Model model, @ModelAttribute("scri") SearchCriteriaAdminUser scri, @RequestParam(name = "page", required = false, defaultValue = "1") int page, HttpSession session) throws Exception {
		String signedInUser = (String) session.getAttribute("signedInUser");
	   	if(!(signedInUser==null)) {
	   		User user = adminuserservice.selectById(signedInUser);
	   		model.addAttribute("user", user);
	   	}
		
		model.addAttribute("faqboard_posts", faqService.read(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(faqService.listCount(scri));

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("page", page);

		return "/forum/faqboard";
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
	
	@GetMapping({"/faqboard-detail", "/faqboard-modify"})
	//-> /faqboard-detail, /faqboard-modify 2개의 요청을 처리하는 메서드!
	public void faqboard_detail(@RequestParam(name = "faq_id") long faq_id, Model model, HttpSession session) {
	
	String signedInUser = (String) session.getAttribute("signedInUser");
	if(!(signedInUser==null)) {
		User user = adminuserservice.selectById(signedInUser);
	   	model.addAttribute("user",user);
	}

	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
	Faq faq = faqService.detail(faq_id);
	
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 조회수 내용을 읽음.
	faqService.addView(faq_id);
	
	// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
	model.addAttribute("faq", faq);
	}
	
	@PostMapping("/faqboard-modify/{faq_id}") //-> 게시글 업데이트를 위한 메서드...
	public ResponseEntity<Integer> faqboard_modify(@RequestBody FaqModifyDto dto, @PathVariable long faq_id) {
		log.debug("=====================================");
		log.debug("DTO = {}, FAQ_ID = {}", dto, faq_id);
		log.debug("=====================================");
		
		int result = faqService.update(dto, faq_id);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping({"faqboard-detail/delete", "faqboard-modify/delete"}) //-> 게시글 삭제...
	/*
	 * 누가.. faqboard-detail/delete 요청 주소를 보냈을까? -> 자바스크립트 파일에서 보냄.
	 * faqboard-detail.js 파일에서 location.href = `faqboard-detail/delete?faq_id=${faqId.value}`; 가 실행되어,
	 * localhost:8081/fourmen/forum/faqboard-detail/delete?faq_id=(번호) 요청이 서버로 전달된 것이다!
	 */
	public String faqboard_delete(long faq_id) {
		
		faqService.delete(faq_id);
		
		return "redirect:/forum/faqboard";
	}
	
	@GetMapping("faqboard_search") //-> GET 방식의 "forum/faqboard_search" 요청 주소를 처리하는 메서드.
	public String search(FaqSearchDto dto, Model model) {
    	log.debug("search(dto={})", dto);
    
		// 서비스 계층의 메서드를 호출해서 검색 서비스를 수행.
    	List<FaqListItemDto> list = faqService.search(dto);
    
    	// 검색 결과를 뷰에 전달하기 위해서 모델 속성(attribute)에 추가.
    	model.addAttribute("faqboard_posts", list);
    	
    	return "forum/faqboard"; //-> 뷰의 경로(/WEB-INF/views/forum/qnaboard.jsp)
    	//-> servlet-context에서 prefix 부분이 /WEB-INF/views/ 였으므로.
	}
	// faqboard 처리 끝 //
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// noticeboard 처리 시작 //
//	@GetMapping("/noticeboard")
//		public void notice(Model noticeboardModel) {
//		//-> 디스패쳐 서블릿에게 뷰에 전달할 데이터를 저장할 모델 객체를 요청해서 받음.
//		// model: 데이터를 담는 그릇 역할, map 구조로 저장됨, key와 value로 구성.
//		
//		// noticeService의 메서드를 호출해서 포스트 목록을 만들고 뷰에 전달.
//		List<NoticeListItemDto> list = noticeService.read();
//		noticeboardModel.addAttribute("noticeboard_posts", list); //-> 뷰에 전달되는 데이터.
//		
//		log.debug("공지게시판 리스트 사이즈 = {}", list.size());
//		
//		// 리턴 값이 없으면 요청 경로로 뷰(JSP)를 찾음.
//		//-> /WEB-INF/views/forum/noticeboard.jsp
//	}
	
	@RequestMapping(value = "/noticeboard", method = RequestMethod.GET)
	public String noticeboard_list(Model model, @ModelAttribute("scri") SearchCriteriaAdminUser scri, @RequestParam(name = "page", required = false, defaultValue = "1") int page, HttpSession session) throws Exception {
		String signedInUser = (String) session.getAttribute("signedInUser");
	   	if(!(signedInUser==null)) {
	   		User user = adminuserservice.selectById(signedInUser);
	   		model.addAttribute("user", user);
	   	}
		
		model.addAttribute("noticeboard_posts", noticeService.read(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(noticeService.listCount(scri));

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("page", page);

		return "/forum/noticeboard";
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
	
	@GetMapping({"/noticeboard-detail", "/noticeboard-modify"})
	//-> /noticeboard-detail, /noticeboard-modify 2개의 요청을 처리하는 메서드!
	public void noticeboard_detail(@RequestParam(name = "notice_id") long notice_id, Model model, HttpSession session) {
	
	String signedInUser = (String) session.getAttribute("signedInUser");
	if(!(signedInUser==null)) {
	   	User user = adminuserservice.selectById(signedInUser);
	   	model.addAttribute("user",user);
	}
	   	
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 상세보기 내용을 읽음.
	Notice notice = noticeService.detail(notice_id);
	
	// 서비스 계층의 메서드를 호출해서 뷰에 전달할 포스트 조회수 내용을 읽음.
	noticeService.addView(notice_id);
	
	// 뷰에 데이터를 전달하기 위해서 모델에 데이터를 속성으로 추가.
	model.addAttribute("notice", notice);
	}
	
	@PostMapping("/noticeboard-modify/{notice_id}") //-> 게시글 업데이트를 위한 메서드...
	public ResponseEntity<Integer> noticeboard_modify(@RequestBody NoticeModifyDto dto, @PathVariable long notice_id) {
		log.debug("=====================================");
		log.debug("DTO = {}, NOTICE_ID = {}", dto, notice_id);
		log.debug("=====================================");
		
		int result = noticeService.update(dto, notice_id);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping({"noticeboard-detail/delete", "noticeboard-modify/delete"}) //-> 게시글 삭제...
	/*
	 * 누가.. noticeboard-detail/delete 요청 주소를 보냈을까? -> 자바스크립트 파일에서 보냄.
	 * noticeboard-detail.js 파일에서 location.href = `noticeboard-detail/delete?faq_id=${noticeId.value}`; 가 실행되어,
	 * localhost:8081/fourmen/forum/noticeboard-detail/delete?notice_id=(번호) 요청이 서버로 전달된 것이다!
	 */
	public String noticeboard_delete(long notice_id) {
		
		noticeService.delete(notice_id);
		
		return "redirect:/forum/noticeboard";
	}
	
	@GetMapping("noticeboard_search") //-> GET 방식의 "forum/noticeboard_search" 요청 주소를 처리하는 메서드.
	public String search(NoticeSearchDto dto, Model model) {
    	log.debug("search(dto={})", dto);
    
		// 서비스 계층의 메서드를 호출해서 검색 서비스를 수행.
    	List<NoticeListItemDto> list = noticeService.search(dto);
    
    	// 검색 결과를 뷰에 전달하기 위해서 모델 속성(attribute)에 추가.
    	model.addAttribute("noticeboard_posts", list);
    	
    	return "forum/noticeboard"; //-> 뷰의 경로(/WEB-INF/views/forum/qnaboard.jsp)
    	//-> servlet-context에서 prefix 부분이 /WEB-INF/views/ 였으므로.
	}
	// noticeboard 처리 끝 //
	
}