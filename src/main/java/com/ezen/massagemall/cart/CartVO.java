package com.ezen.massagemall.cart;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CartVO {

	private Integer pro_num;
	private String mc_email;
	private int cart_amount;
	private LocalDateTime cart_date;
}
