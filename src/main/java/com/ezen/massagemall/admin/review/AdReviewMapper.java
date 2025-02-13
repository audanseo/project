package com.ezen.massagemall.admin.review;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.massagemall.admin.utils.SearchCriteria;
import com.ezen.massagemall.review.ReviewReply;
import com.ezen.massagemall.review.ReviewVO;

public interface AdReviewMapper {

	List<ReviewVO> review_list(@Param("cri") SearchCriteria cri, @Param("rev_rate") String rev_rate,
			@Param("rev_content") String rev_content);

	int review_count(@Param("cri") SearchCriteria cri, @Param("rev_rate") String rev_rate,
			@Param("rev_content") String rev_content);

	ReviewReply reply_info(Integer reply_id);

	void reply_modify(@Param("reply_id") Integer reply_id, @Param("reply_text") String reply_text);

	void reply_delete(Integer reply_id);
}
