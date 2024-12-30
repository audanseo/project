package com.ezen.massagemall.admin.category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CategoryVO {

	private Integer cate_code; // 1차
	private Integer cate_prtcode;// 2차
	private String cate_name;
}
