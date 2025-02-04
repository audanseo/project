package com.ezen.massagemall.review;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.massagemall.admin.utils.SearchCriteria;
import com.ezen.massagemall.product.ProductMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

	private final ReviewMapper reviewMapper;
	private final ProductMapper productMapper;

	public List<ReviewVO> rev_list(Integer pro_num, SearchCriteria cri) {
		return reviewMapper.rev_list(pro_num, cri);
	}

	public int getCountReviewByPro_num(Integer pro_num) {
		return reviewMapper.getCountReviewByPro_num(pro_num);
	}

	@Transactional
	public void review_save(ReviewVO vo) {
		// 상품후기등록
		reviewMapper.review_save(vo);
		// 상품테이블 후기 카운트작업
		productMapper.review_count(vo.getPro_num());
	}

	public ReviewVO review_info(Integer rev_code) {
		return reviewMapper.review_info(rev_code);
	}

	public void review_modify(ReviewVO vo) {
		reviewMapper.review_modify(vo);
	}

	public void review_delete(Integer rev_code) {
		reviewMapper.review_delete(rev_code);
	}
}
