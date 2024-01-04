package com.itwill.fourmen.web;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.dto.myinfo.MyInfoUpdateDto;
import com.itwill.fourmen.service.MyPageInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
@RestController
public class MyPageMyInfoRestController {
	
	private final MyPageInfoService myPageInfoService;
	
	@PostMapping("/temp-profile")
	public ResponseEntity<String> InsertTemporaryPhoto(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		
		log.debug("InsertTemporaryPhoto(file={})", file);
		
		String sDirectory = request.getServletContext().getRealPath("/static/image");
		log.debug("sDirectory={}", sDirectory);
		
		String savedFileName = myPageInfoService.uploadTemporaryImage(file, sDirectory);
		log.debug("savedFileName={}", savedFileName);
		
		return ResponseEntity.ok(savedFileName);
	}
	
	
	/**
	 * 임시로 저장한 프로필 사진을 삭제하는 rest controller메서드
	 * @param newSFileName
	 * @param request
	 * @return 삭제 성공시 1, 실패시 0
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@DeleteMapping("/temp-profile/delete/{newSFileName}")
	public ResponseEntity<Integer> deleteTemporaryPhoto(@PathVariable String newSFileName, HttpServletRequest request) throws IllegalStateException, IOException {
		boolean didWork = false;
		int result = 0;
		
		log.debug("deleteTemporaryPhoto(newSFileName={})", newSFileName);
		
		String sDirectory = request.getServletContext().getRealPath("/static/image");
		log.debug("sDirectory={}", sDirectory);
		
		didWork = myPageInfoService.deleteTemporaryImage(newSFileName, sDirectory);
		if (didWork) {
			result = 1;
		}
		
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping("/myinfo/changepassword")
	public ResponseEntity<Integer> updatePassword(@RequestBody MyInfoUpdateDto updateDto, HttpSession session) {
		String signedInUser = (String) session.getAttribute("signedInUser");		
		log.debug("updatePassword(signedInUser={}, updateDto={}})", signedInUser, updateDto);
		
		updateDto.setUserid(signedInUser);
		int result = myPageInfoService.updatePassword(updateDto);
		
		log.debug("result={}", result);
		
		return ResponseEntity.ok(result);
	}

}
