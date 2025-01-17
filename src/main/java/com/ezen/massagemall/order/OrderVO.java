package com.ezen.massagemall.order;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class OrderVO {

	private Integer ord_code;
	private String mc_email;
	private String ord_name;
	private String ord_zipcode;
	private String ord_addr;
	private String ord_deaddr;
	private String ord_phone;
	private int ord_price;
	private int ord_rentalprice;
	private String ord_status;
	private LocalDateTime ord_date;

}
