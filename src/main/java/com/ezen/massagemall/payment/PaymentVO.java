package com.ezen.massagemall.payment;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PaymentVO {

	private Integer payment_id;
	private Integer ord_code;
	private String mc_email;
	private String payment_method;
	private int payment_price;
	private String payment_status;
	private Date payment_date;
}
