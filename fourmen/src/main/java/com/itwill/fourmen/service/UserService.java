package com.itwill.fourmen.service;

import org.springframework.stereotype.Service;

import com.itwill.fourmen.domain.User;
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
}
