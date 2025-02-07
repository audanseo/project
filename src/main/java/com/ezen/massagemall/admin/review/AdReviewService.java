package com.ezen.massagemall.admin.review;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ezen.massagemall.admin.utils.SearchCriteria;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdReviewService {

	private final AdReviewMapper adReviewMapper;

	public List<Map<String, Object>> review_list(SearchCriteria cri, String rev_rate, String rev_content) {
		return adReviewMapper.review_list(cri, rev_rate, rev_content);
	}

	public int review_count(SearchCriteria cri, String rev_rate, String rev_content) {
		return adReviewMapper.review_count(cri, rev_rate, rev_content);
	}
}
