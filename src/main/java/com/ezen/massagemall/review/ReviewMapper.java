package com.ezen.massagemall.review;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.massagemall.admin.utils.SearchCriteria;

public interface ReviewMapper {

	List<ReviewVO> rev_list(@Param("pro_num") Integer pro_num, @Param("cri") SearchCriteria cri);

	int getCountReviewByPro_num(Integer pro_num);

	void review_save(ReviewVO vo);
}
