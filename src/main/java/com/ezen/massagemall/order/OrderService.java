package com.ezen.massagemall.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.massagemall.admin.utils.SearchCriteria;
import com.ezen.massagemall.cart.CartMapper;
import com.ezen.massagemall.payment.PaymentMapper;
import com.ezen.massagemall.payment.PaymentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderMapper orderMapper;
	private final PaymentMapper paymentMapper;
	private final CartMapper cartMapper;

	// 주문하기(주문테이블, 주문상세테이블, 결제테이블, 장바구니테이블) 여러개를 사용하기 위해 transactional 사용
	@Transactional
	public void order_process(OrderVO vo, String mc_email, String p_method) {

		log.info("주문번호" + vo.getOrd_code());

		// 1)주문테이블
		orderMapper.order_insert(vo);

		// 2)주문 상세테이블

		orderMapper.order_detail_insert(vo.getOrd_code(), vo.getMc_email());
		// 3)결제 테이블

		PaymentVO p_vo = new PaymentVO();
		p_vo.setOrd_code(vo.getOrd_code());
		p_vo.setMc_email(vo.getMc_email());

		p_vo.setPayment_method(p_method); // 카카오페이
		p_vo.setPayment_price(vo.getOrd_price()); // 총구매 금액

		if (p_method.equals("카카오페이")) {
			p_vo.setPayment_status("입금완료");
		} else if (p_method.contains("계좌이체")) {
			p_vo.setPayment_status("입금미납");
		}

		paymentMapper.payment_insert(p_vo);

		// 4)장바구니 테이블
		cartMapper.cart_empty(vo.getMc_email());
	}

	// 실시간 결제에 따른 주문내역
	public List<Map<String, Object>> getOrderInfoOrd_code(Integer ord_code) {
		return orderMapper.getOrderInfoOrd_code(ord_code);
	}

	public List<Map<String, Object>> getOrderListByUser_id(String mc_email, SearchCriteria cri) {
		return orderMapper.getOrderListByUser_id(mc_email, cri);
	}

	public int getOrderCountByUser_id(String mc_email) {
		return orderMapper.getOrderCountByUser_id(mc_email);
	}
}
