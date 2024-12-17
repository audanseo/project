package com.ezen.massagemall.admin.product;

import java.util.List;

public interface AdProductMapper {

	void pro_insert(ProductVO vo);

	List<ProductVO> pro_list();
}
