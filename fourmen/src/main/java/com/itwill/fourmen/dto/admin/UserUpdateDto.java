package com.itwill.fourmen.dto.admin;

import com.itwill.fourmen.domain.User;
import com.itwill.fourmen.dto.user.UserSignUpDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDto {

		 	private String userid;
		    private String password;
		    private String name;
		    private String nickname;
		    private String phone;
		    private String email;
		    private String grade;

	  public User toEntity() {
		  return User.builder().userid(userid).password(password)
				  .name(name).nickname(nickname).phone(phone).email(email).grade(grade).build();
	  }
}
