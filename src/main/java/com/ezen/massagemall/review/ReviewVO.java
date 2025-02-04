package com.ezen.massagemall.review;

import java.time.LocalDateTime;
import java.util.List;

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
	private LocalDateTime rev_date;
	private String rev_nickname;
	private List<ReviewReply> replies;
}
