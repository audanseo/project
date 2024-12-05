package com.ezen.massagemall.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class LoginDTO {
	private String mc_email;
	private String mc_password;
}
