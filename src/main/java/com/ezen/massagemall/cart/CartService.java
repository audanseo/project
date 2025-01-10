package com.ezen.massagemall.cart;

import java.util.HashMap;
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

	public Integer getCartTotalPriceByUserId(String mc_email) {
		return cartMapper.getCartTotalPriceByUserId(mc_email);
	}

	public void cart_change(CartVO vo) {
		cartMapper.cart_change(vo);
	}

	public void cart_sel_delete(int[] pro_num, String mc_email) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("pro_num_arr", pro_num);
		map.put("mc_email", mc_email);

		cartMapper.cart_sel_delete(map);
	}

	public void cart_empty(String mc_email) {
		cartMapper.cart_empty(mc_email);
	}
}
