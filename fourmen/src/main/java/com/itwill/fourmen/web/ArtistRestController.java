package com.itwill.fourmen.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.fourmen.service.ArtistService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/artist")
public class ArtistRestController {

	private final ArtistService artistService;
	
	@DeleteMapping("/artist_details/{userid}")
	public ResponseEntity<Integer> deleteArtist(@PathVariable String userid, HttpServletRequest request){
		log.debug("deleteArtist(userid = {})", userid);
		
		ServletContext sDirectory = request.getServletContext();
		
		int result = artistService.deleteArtist(userid, sDirectory);
		
		return ResponseEntity.ok(result);
	}
	
}
