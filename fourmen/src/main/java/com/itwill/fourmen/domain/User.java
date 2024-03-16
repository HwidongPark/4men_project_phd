package com.itwill.fourmen.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
    private String userid;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private String email;
    private String grade;
    private String profile_o_img;
    private String profile_s_img;
}
