package com.ezen.massagemall.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserMapper userMapper;

	public void join(UserVO vo) {
		userMapper.join(vo);

	}

	public String emailcheck(String mc_email) {
		return userMapper.emailcheck(mc_email);
	}

	public UserVO login(String mc_email) {
		return userMapper.login(mc_email);
	}

	public UserVO modify(String mc_email) {
		return userMapper.modify(mc_email);
	}
}
