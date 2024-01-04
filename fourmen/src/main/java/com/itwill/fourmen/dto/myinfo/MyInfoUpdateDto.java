package com.itwill.fourmen.dto.myinfo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyInfoUpdateDto {

	private String userid;
	private String nickname;
	private Long phone;
	private String email;
	private String password;
	private String newPassword;
	private String profile_o_img;
	private String profile_s_img;
	private MultipartFile profileImage;
		

}
