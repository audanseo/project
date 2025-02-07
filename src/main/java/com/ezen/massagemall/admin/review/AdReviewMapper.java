package com.ezen.massagemall.admin.review;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ezen.massagemall.admin.utils.SearchCriteria;

public interface AdReviewMapper {

	List<Map<String, Object>> review_list(@Param("cri") SearchCriteria cri, @Param("rev_rate") String rev_rate,
			@Param("rev_content") String rev_content);

	int review_count(@Param("cri") SearchCriteria cri, @Param("rev_rate") String rev_rate,
			@Param("rev_content") String rev_content);
}
