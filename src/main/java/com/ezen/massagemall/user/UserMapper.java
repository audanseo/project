package com.ezen.massagemall.user;

public interface UserMapper {

	void join(UserVO vo);

	String emailcheck(String mc_email);

	UserVO login(String mc_email);

	UserVO modify(String mc_email);
}
