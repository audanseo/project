package com.ezen.massagemall.admin.product;

import java.util.List;

import com.ezen.massagemall.admin.category.CategoryVO;
import com.ezen.massagemall.admin.utils.SearchCriteria;

public interface AdProductMapper {

	void pro_insert(ProductVO vo);

	List<ProductVO> pro_list(SearchCriteria cri);

	int getTotalCount(SearchCriteria cri);

	List<CategoryVO> getSecondCategoryList(Integer cate_prtcode);
}
