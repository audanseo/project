package com.ezen.massagemall.admin.product;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ProductVO {

	private Integer pro_num;
	private Integer cate_code;
	private String pro_name;
	private int pro_rentalprice;
	private int pro_price;
	private int pro_discount;
	private String pro_content;
	private String pro_upfolder;
	private String pro_img;
	private int pro_amount;
	private String pro_buy;
	private Date pro_date;
	private Date pro_updatedate;
	private int pro_review;
}
