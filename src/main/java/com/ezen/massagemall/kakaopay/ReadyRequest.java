package com.ezen.massagemall.kakaopay;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

//결제준비(ready) 요청파라미터
@Getter
@AllArgsConstructor // 모든필드를 대상으로 생성자생성
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReadyRequest {

	private String cid; // 가맹점코드 10자
	private String partner_order_id; // 가맹점 주문번호
	private String partner_user_id; // 가맹점 회원id
	private String item_name; // 상품명
	private Integer quantity; // 상품수량
	private Integer total_amount; // 상품총액
	private Integer tax_free_amount; // 상품비과세금액
	private String approval_url;
	private String cancel_url;
	private String fail_url;

	public ReadyRequest(String cid, String partner_order_id) {
		this.cid = cid;
		this.partner_order_id = partner_order_id;
	}
}
