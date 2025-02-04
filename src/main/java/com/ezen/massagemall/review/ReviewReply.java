package com.ezen.massagemall.review;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReviewReply {

	private Integer reply_id;
	private Integer rev_code;
	private String manager_id;
	private String reply_text;
	private LocalDateTime reply_date;
}
