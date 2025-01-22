package com.ezen.massagemall.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ezen.massagemall.admin.utils.SearchCriteria;

public interface OrderMapper {

	void order_insert(OrderVO vo);

	void order_detail_insert(@Param("ord_code") Integer ord_code, @Param("mc_email") String mc_email);

	List<Map<String, Object>> getOrderInfoOrd_code(Integer ord_code);

	List<Map<String, Object>> getOrderListByUser_id(@Param("mc_email") String mc_email,
			@Param("cri") SearchCriteria cri);

	int getOrderCountByUser_id(String mc_email);

}
