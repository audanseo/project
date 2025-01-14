package com.ezen.massagemall.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CartMapper {

	void cart_add(CartVO vo);

	List<Map<String, Object>> getCartDetailsByUserId(String mc_email);

	List<Map<String, Object>> cart_list(String mc_email);

	Integer getCartTotalPriceByUserId(String mc_email);

	void cart_change(CartVO vo);

	void cart_sel_delete(HashMap<String, Object> map);

	void cart_empty(String mc_email);
}
