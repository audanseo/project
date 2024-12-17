package com.ezen.massagemall.admin.product;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdProductService {

	private final AdProductMapper adProductMapper;

	public void pro_insert(ProductVO vo) {
		adProductMapper.pro_insert(vo);
	}

	public List<ProductVO> pro_list() {
		return adProductMapper.pro_list();
	}
}
