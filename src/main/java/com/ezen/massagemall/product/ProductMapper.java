package com.ezen.massagemall.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.massagemall.admin.product.ProductVO;
import com.ezen.massagemall.admin.utils.SearchCriteria;

public interface ProductMapper {

	List<ProductVO> getProductListBysecondCategory(@Param("cri") SearchCriteria cri,
			@Param("cate_code") Integer seconde_cate_code);

	ProductVO pro_info(Integer pro_num);

	void review_count(Integer pro_num);

	int review_count_pro_info(Integer pro_num);
}
