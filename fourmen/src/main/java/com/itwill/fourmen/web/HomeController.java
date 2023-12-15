package com.itwill.fourmen.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwill.fourmen.domain.Exhibition;
import com.itwill.fourmen.service.ExhibitionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller // 컨트롤러 컴포넌트
public class HomeController {
	
	private final ExhibitionService exhibitionService;
	
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
    
    @GetMapping("/exhibition")
    public void exhibition(Model model) {
    	log.debug("exhibition()");
    	
    	List<Exhibition> exhibition= exhibitionService.read();
    	log.debug("exhibition={}",exhibition);
    	model.addAttribute("exhibition",exhibition);
    }
       
    @GetMapping("/forum")
    public void forum() {
    	log.debug("forum()");
    }
    
    @GetMapping("/market")
    public void market() {
    	log.debug("market()");
    }
    
    @GetMapping("/admin")
    public void admin() {
    	log.debug("forum()");
    }
    
}
// HomeController
// @Component (super) <-- @Controller, @Service , @Repository (sub)