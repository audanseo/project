package com.ezen.massagemall.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class OrderDetailVO {

	private Integer ord_code;
	private Integer pro_num;
	private int dt_amount;
	private int dt_price;
}
