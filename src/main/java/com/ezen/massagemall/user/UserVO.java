package com.ezen.massagemall.user;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class UserVO {

	private String mc_email;
	private String mc_password;
	private String mc_name;
	private String mc_phone;
	private String mc_zipcode;
	private String mc_addr;
	private String mc_deaddr;
	private Date mc_regdate;
	private Date mc_lastlogin;
}
