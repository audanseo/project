package com.ezen.massagemall.admin.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.massagemall.admin.category.CategoryVO;
import com.ezen.massagemall.admin.utils.SearchCriteria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdProductService {

	private final AdProductMapper adProductMapper;

	public void pro_insert(ProductVO vo) {
		adProductMapper.pro_insert(vo);
	}

	public List<ProductVO> pro_list(SearchCriteria cri) {
		return adProductMapper.pro_list(cri);
	}

	public List<CategoryVO> getSecondCategoryList(Integer cate_prtcode) {
		return adProductMapper.getSecondCategoryList(cate_prtcode);
	}

	public int getTotalCount(SearchCriteria cri) {
		return adProductMapper.getTotalCount(cri);
	}
}
