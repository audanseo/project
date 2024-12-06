package com.ezen.massagemall.user;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

	void join(UserVO vo);

	String emailcheck(String mc_email);

	UserVO login(String mc_email);

	UserVO modify(String mc_email);

	void modify_save(UserVO vo);

	void pwchange(@Param("mc_email") String mc_email, @Param("mc_password") String mc_password);

	String emailsearch(@Param("mc_name") String mc_name, @Param("mc_phone") String mc_phone);
}
