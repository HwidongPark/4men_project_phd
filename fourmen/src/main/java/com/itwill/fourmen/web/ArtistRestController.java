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
@RequestMapping("/api/works")
public class ArtistRestController {

	private final ArtistService artistService;
	
	@DeleteMapping("/{worksid}")
	public ResponseEntity<Integer> deleteWorks(@PathVariable long worksid, HttpServletRequest request) {
		log.debug("deleteWorks(WORKSID = {})", worksid);
		
		String sDirectory = request.getServletContext().getRealPath("/static/images/works");
		
		int result = artistService.deleteWorks(worksid, sDirectory);
		
		return ResponseEntity.ok(result);
	}
	
}
