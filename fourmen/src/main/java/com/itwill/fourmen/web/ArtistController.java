package com.itwill.fourmen.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/artist")
public class ArtistController {

	@GetMapping("/artist_details")
    public void details() {
    	log.debug("details()");
    }
	
	@GetMapping("/works")
	public void works() {
		log.debug("works()");
	}
	
}
