package com.ezen.massagemall.kakaopay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaopayService {

	@Value("${readUrl}")
	private String readUrl;

	@Value("${approveUrl}")
	private String approveUrl;

	@Value("${secretKey}")
	private String secretKey;

	@Value("${cid}")
	private String cid;

	@Value("${approval}")
	private String approval;

	@Value("${cancel}")
	private String cancel;

	@Value("${fail}")
	private String fail;

	private String tid;

	private String partner_order_id;

	private String partner_user_id;

	// 결제준비요청
	public ReadyResponse ready(String partner_order_id, String partner_user_id, String item_name, Integer quantity,
			Integer total_amount, Integer tax_free_amount) {

		HttpHeaders headers = getHttpHeaders();

		ReadyRequest readyRequest = ReadyRequest.builder().cid(cid).partner_order_id(partner_order_id)
				.partner_user_id(partner_user_id).item_name(item_name).quantity(quantity).total_amount(total_amount)
				.tax_free_amount(tax_free_amount).approval_url(approval).cancel_url(cancel).fail_url(fail).build();

		// data request 결제준비요청에 보낼 헤더와 파라미터를 가지고있는 객체 작업
		HttpEntity<ReadyRequest> entityMap = new HttpEntity<>(readyRequest, headers);

		// 결제준비요청
		ResponseEntity<ReadyResponse> response = new RestTemplate().postForEntity(readUrl, entityMap,
				ReadyResponse.class);

		// 응답데이터
		ReadyResponse readyResponse = response.getBody();

		this.tid = readyResponse.getTid();
		this.partner_order_id = partner_order_id;
		this.partner_user_id = partner_user_id;

		return readyResponse;
	}

	// 결제승인요청-approve
	public String approve(String pg_token) {
		HttpHeaders headers = getHttpHeaders();

		ApproveRequest approveRequest = ApproveRequest.builder().cid(cid).tid(tid).partner_order_id(partner_order_id)
				.partner_user_id(partner_user_id).pg_token(pg_token).build();

		// data request 결제승인요청에 보낼 헤더와 파라미터를 가지고 있는 객체작업
		HttpEntity<ApproveRequest> entityMap = new HttpEntity<ApproveRequest>(approveRequest, headers);

		ResponseEntity<ApproveResponse> response = new RestTemplate().postForEntity(approveUrl, entityMap,
				ApproveResponse.class);

		return response.toString();
	}

	public HttpHeaders getHttpHeaders() {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "SECRET_KEY " + secretKey);
		headers.set("Content-Type", "application/json;charset=utf-8");

		return headers;
	}

}
