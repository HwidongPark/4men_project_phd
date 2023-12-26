package com.itwill.fourmen.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.fourmen.service.ArtistService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/artist")
public class ArtistRestController {
	
	private final ArtistService artistService;
	
//	@DeleteMapping("/delete/images/{userid}")
//	public ResponseEntity<Integer> deleteProfileImage (@PathVariable String userid, HttpServletRequest request){
//		log.debug("ArtistRestController - deleteProfileImage()");
//		log.debug("ArtistRestController - deleteProfileImage - USERID = {}", userid);
//		
//		String sDirectory = request.getServletContext().getRealPath("/static/images/char");
//		log.debug("sDirectory = {}",sDirectory);
//		
//		int result = artistService.deleteProfileImg(userid, sDirectory);
//		
//		return ResponseEntity.ok(result);
//	}
	
	
}
