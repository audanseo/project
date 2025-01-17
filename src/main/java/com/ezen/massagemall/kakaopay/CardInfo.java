package com.ezen.massagemall.kakaopay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CardInfo {

	private String kakaopay_purchase_corp;
	private String kakaopay_purchase_corp_code;
	private String kakaopay_issuer_corp;
	private String kakaopay_issuer_corp_code;
	private String bin;
	private String card_type;
	private String install_month;
	private String approved_id;
	private String card_mid;
	private String interest_free_install;
	private String installment_type;
	private String card_item_code;
}
