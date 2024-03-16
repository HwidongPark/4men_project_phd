package com.itwill.fourmen.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.dto.myinfo.MyInfoUpdateDto;
import com.itwill.fourmen.dto.user.UserSignInDto;
import com.itwill.fourmen.dto.user.UserSignUpDto;
import com.itwill.fourmen.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	private final UserDao userdao;
	
	public int create(UserSignUpDto dto) {
		log.debug("create= {}",dto);
		int result = userdao.insert(dto.toEntity());
		
		return result;
	}
	
	public boolean checkUserid(String userid) {
		User user= userdao.selectByUserid(userid);
		if(user == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkNickname(String nickname) {
		User user= userdao.selectByNickname(nickname);
		if(user == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public User login(UserSignInDto dto) {
		log.debug("login dto = {}",dto);
		User user = userdao.selectByUseridAndPassword(dto);
		
		return user;
	}
	
	public User selectUserById(String userid) {
		log.debug("selectUserById(userid={})", userid);
		
		User user = userdao.selectByUserid(userid);
		log.debug("user={}", user);
		
		return user;
	}
	
	// TODO: 하는 중
	/**
	 * my page의 내 정보 페이지에서 내 정보를 업데이트하는 서비스 메서드
	 * @param updateDto
	 * @param sDirectory
	 * @return 성공시 1, 실패시 0
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public int updateUser(MyInfoUpdateDto updateDto, String sDirectory) throws IllegalStateException, IOException {
		log.debug("updateUser(updateDto={})", updateDto);
		
		MultipartFile file = updateDto.getProfileImage();
		if (!file.isEmpty()) {
			String originalFileName = file.getOriginalFilename();
			log.debug("originalFileName={}", originalFileName);
			
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			log.debug("extension={}", extension);
			
			// 새로운 파일 이름 구함
			String savedFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "profile" + extension;
			log.debug("새 파일 이름={}", savedFileName);
			
			updateDto.setProfile_o_img(originalFileName);
			updateDto.setProfile_s_img(savedFileName);
			
			
			// 파일을 저장
			String absolutePath = sDirectory + File.separator + savedFileName;
			log.debug("파일절대경로={}", absolutePath);
			file.transferTo(new File(absolutePath));
		}

		
		int result = userdao.updateUserInfo(updateDto);
		log.debug("업데이트 결과={}", result);
		
		
		return result;
	}
	
}
