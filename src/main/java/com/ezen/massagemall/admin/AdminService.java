package com.ezen.massagemall.admin;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {

	private final AdminMapper adminMapper;

	public AdminDto admin_ok(String ad_userid) {
		return adminMapper.admin_ok(ad_userid);
	}
}
