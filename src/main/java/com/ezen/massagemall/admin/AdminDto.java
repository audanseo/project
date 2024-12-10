package com.ezen.massagemall.admin;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AdminDto {
	private String ad_userid;
	private String ad_passwd;
	private Date login_date;
}
