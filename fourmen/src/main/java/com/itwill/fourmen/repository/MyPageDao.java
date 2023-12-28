package com.itwill.fourmen.repository;

import com.itwill.fourmen.domain.Message;

public interface MyPageDao {
	
	int sendMessage(Message message);
	
}
