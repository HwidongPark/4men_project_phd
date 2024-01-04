package com.itwill.fourmen.repository;

import com.itwill.fourmen.dto.myinfo.MyInfoUpdateDto;

public interface MyPageInfoDao {
	
	// 유저가 작성한 마켓 글들의 총 개수를 가져옴
	int countTotNumber(String signedInUser);	
	
	// 새로운 비밀번호로 업데이트
	int updatePassword(MyInfoUpdateDto updateDto);
	
}
