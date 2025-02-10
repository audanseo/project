package com.ezen.massagemall.admin.review;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.massagemall.admin.utils.SearchCriteria;
import com.ezen.massagemall.review.ReviewReply;
import com.ezen.massagemall.review.ReviewVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdReviewService {

	private final AdReviewMapper adReviewMapper;

	public List<ReviewVO> review_list(SearchCriteria cri, String rev_rate, String rev_content) {
		return adReviewMapper.review_list(cri, rev_rate, rev_content);
	}

	public int review_count(SearchCriteria cri, String rev_rate, String rev_content) {
		return adReviewMapper.review_count(cri, rev_rate, rev_content);
	}

	public ReviewReply reply_info(Integer reply_id) {
		return adReviewMapper.reply_info(reply_id);
	}
}
