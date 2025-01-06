package com.ezen.massagemall.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.massagemall.admin.product.ProductVO;
import com.ezen.massagemall.admin.utils.SearchCriteria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductMapper productMapper;

	public List<ProductVO> getProductListBysecondCategory(SearchCriteria cri, Integer seconde_cate_code) {
		return productMapper.getProductListBysecondCategory(cri, seconde_cate_code);
	}

	public ProductVO pro_info(Integer pro_num) {
		return productMapper.pro_info(pro_num);
	}
}
