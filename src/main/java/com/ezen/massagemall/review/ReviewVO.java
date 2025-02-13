package com.ezen.massagemall.review;

import java.util.Date;
import java.util.List;

import com.ezen.massagemall.admin.product.ProductVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReviewVO {

	private Integer rev_code;
	private String mc_email;
	private Integer pro_num;
	private String rev_title;
	private String rev_content;
	private int rev_rate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Date rev_date;
	private String rev_nickname;

	private ProductVO product;

	// 상품 후기 답변
	private List<ReviewReply> replies;
}
