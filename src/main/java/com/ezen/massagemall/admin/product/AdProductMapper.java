package com.ezen.massagemall.admin.product;

import java.util.List;

import com.ezen.massagemall.admin.category.CategoryVO;
import com.ezen.massagemall.admin.utils.SearchCriteria;

public interface AdProductMapper {

	void pro_insert(ProductVO vo);

	List<ProductVO> pro_list(SearchCriteria cri);

	List<CategoryVO> getFirstCategoryList();

	List<CategoryVO> getSecondCategoryList(Integer cate_prtcode);

	CategoryVO getFirstCategoryBySecondCategory(int secondCategory);

	int getTotalCount(SearchCriteria cri);

	ProductVO pro_edit_form(Integer pro_num);

	void pro_edit(ProductVO vo);
}
