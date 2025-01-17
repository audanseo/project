package com.ezen.massagemall.kakaopay;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ApproveResponse {

	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type;

	private Amount amount;
	private CardInfo cardInfo;

	private String item_name;
	private String item_code;
	private Integer quantity;
	private Date created_at;
	private Date approved_at;
	private String payload;
}
