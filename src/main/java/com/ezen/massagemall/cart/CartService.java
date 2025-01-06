package com.ezen.massagemall.cart;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

	private final CartMapper cartMapper;

	public void cart_add(CartVO vo) {
		cartMapper.cart_add(vo);
	}

	public List<Map<String, Object>> cart_list(String mc_email) {
		return cartMapper.cart_list(mc_email);
	}
}
