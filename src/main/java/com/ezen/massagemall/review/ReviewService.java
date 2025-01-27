package com.ezen.massagemall.review;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.massagemall.admin.utils.SearchCriteria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {

	private final ReviewMapper reviewMapper;

	public List<ReviewVO> rev_list(Integer pro_num, SearchCriteria cri) {
		return reviewMapper.rev_list(pro_num, cri);
	}

	public int getCountReviewByPro_num(Integer pro_num) {
		return reviewMapper.getCountReviewByPro_num(pro_num);
	}

	public void review_save(ReviewVO vo) {
		reviewMapper.review_save(vo);
	}
}
