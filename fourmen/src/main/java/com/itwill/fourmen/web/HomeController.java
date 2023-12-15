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
    
    @GetMapping("/forum/freeboard")
    public void forum() {
    	log.debug("forum()");
    }
    
    @GetMapping("/exhibition")
    public void exhibition() {
    	log.debug("exhibition()");
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