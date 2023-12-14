package com.itwill.fourmen.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 컨트롤러 컴포넌트
public class HomeController {

    @GetMapping("/")
    public String home() {
    	log.debug("home()");
        
        return "home"; // view(JSP)의 경로를 리턴
    }
    
    
    @GetMapping("/artist")
    public void artist() {
    	log.debug("artist()");
    }
    
    @GetMapping("/exhibition")
    public void exhibition() {
    	log.debug("exhibition()");
    }
    
    @GetMapping("/forum")
    public void forum() {
    	log.debug("forum()");
    }
    
    // TODO: 나중에 컨트롤러 만들어서 다시 매핑해야함.. 걍 프론트 하려고 이 짓함
    @GetMapping("/market")
    public void market() {
    	log.debug("market()");
    }
    
    @GetMapping("/market/detail")
    public String marketDetail() {
    	log.debug("marketDetail()");
    	
    	return "/market/detail";
    }
    
    
}// HomeController





// @Component (super) <-- @Controller, @Service , @Repository (sub)