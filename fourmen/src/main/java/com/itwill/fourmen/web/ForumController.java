package com.itwill.fourmen.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/forum")
public class ForumController {
	
	@GetMapping("/freeboard")
	public void freeboard() {
		log.debug("freeboard()");
	}
	
}
