package com.ezen.massagemall.cart;

import java.util.List;
import java.util.Map;

public interface CartMapper {

	void cart_add(CartVO vo);

	List<Map<String, Object>> cart_list(String mc_email);
}
