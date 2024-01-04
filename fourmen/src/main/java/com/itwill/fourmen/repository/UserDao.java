package com.itwill.fourmen.repository;

import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.dto.myinfo.MyInfoUpdateDto;
import com.itwill.fourmen.dto.user.UserSignInDto;

public interface UserDao {

	int insert(User user);
	User selectByUserid(String userid);
	User selectByNickname(String nickname);
	User selectByUseridAndPassword(UserSignInDto user);
	
	int updateUserInfo(MyInfoUpdateDto dto);
	
}
