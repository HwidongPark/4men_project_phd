package com.itwill.fourmen.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.fourmen.board.Criteria;
import com.itwill.fourmen.board.PageMaker;
import com.itwill.fourmen.board.SearchCriteria;
import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.dto.user.UserSignInDto;
import com.itwill.fourmen.dto.user.UserSignUpDto;
import com.itwill.fourmen.service.ExhibitionService;
import com.itwill.fourmen.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller // 컨트롤러 컴포넌트
public class HomeController {
	
	private final ExhibitionService exhibitionService;
	private final UserService userservice;
	
    @GetMapping("/")
    public String home() {
    	log.debug("home()");
        
        return "home"; // view(JSP)의 경로를 리턴
    }
    
    @GetMapping("/artist")
    public void artist() {
    	log.debug("artist()");
    }
    
    @GetMapping("/forum/freeboard")
    public void forum() {
    	log.debug("forum()");
    }
    

    @RequestMapping(value = "/exhibition", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
	
		
		model.addAttribute("exhibition", exhibitionService.list(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(exhibitionService.listCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "/exhibition";
		
	}
    
    @GetMapping("/market")
    public void market() {
    	log.debug("market()");
    }
    

    @GetMapping("/market/detail")
    public String marketDetail() {
    	log.debug("marketDetail()");
    	
    	return "/market/detail";
    }
    
    @GetMapping("/admin")
    public void admin() {
    	log.debug("forum()");
    }
    
    @GetMapping("/signup")
    public void signup() {
    	log.debug("signup()");
    }
    
    @PostMapping("/signup")
    public String signup(@ModelAttribute UserSignUpDto dto) {
    	log.debug("Post - signup (dto={})",dto);
    	int result =userservice.create(dto);
    	
    	return "redirect:/signup";
    }
    
    @GetMapping("/checkid")
    @ResponseBody
    public ResponseEntity<String> checkId(@RequestParam(name="userid") String userid){
    	boolean result = userservice.checkUserid(userid);
    	if(result) {
    		return ResponseEntity.ok("Y");
    	} else {
    		return ResponseEntity.ok("N");
    	}
    }
    
    @GetMapping("/checknickname")
    @ResponseBody
    public ResponseEntity<String> checkNickname(@RequestParam(name="nickname") String nickname){
    	boolean result = userservice.checkNickname(nickname);
    	if(result) {
    		return ResponseEntity.ok("Y");
    	} else {
    		return ResponseEntity.ok("N");
    	}
    }
    
    @PostMapping("/signin")
    public String signin(@ModelAttribute UserSignInDto dto, HttpSession session,
    		@RequestParam(name="target", defaultValue="")String target) throws UnsupportedEncodingException {
    		log.debug("POST-signin(dto={},session={},target={})",dto,session,target);
    		
    		User user = userservice.login(dto);
    		if(user !=null) {
    			session.setAttribute("signedInUser", user.getUserid());
    			return (target.equals(""))?"redirect:/" : "redirect:" + target;
    		} else {
    			return "redirect:/signup?result=f&target="+URLEncoder.encode(target,"UTF-8").toString();
    		}
    	
    }
    
    @GetMapping("/signout")
	public String signout(HttpSession session) {
    	session.removeAttribute("signedInUser");
    	session.invalidate();
    	return "redirect:/";
    }
}
// HomeController
// @Component (super) <-- @Controller, @Service , @Repository (sub)
