package com.itwill.fourmen.dto.user;

import com.itwill.fourmen.domain.User;

import lombok.Data;

@Data
public class UserSignUpDto {
	 	private String userid;
	    private String password;
	    private String name;
	    private String nickname;
	    private String phone;
	    private String email;

  public User toEntity() {
	  return User.builder().userid(userid).password(password)
			  .name(name).nickname(nickname).phone(phone).email(email).build();
  }
}
