package com.itwill.fourmen.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
	// 이거 안쓰는거
	private String userid;
	private String name;
	private String nickname;
	private String password;
	private String email;
	private String phone;
	private String artistimg;
	
	
}
