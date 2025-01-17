package com.ezen.massagemall.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderMapper orderMapper;

	// 주문하기(주문테이블, 주문상세테이블, 결제테이블, 장바구니테이블) 여러개를 사용하기 위해 transactional 사용
	@Transactional
	public void order_process(OrderVO vo, String mc_email, String p_method) {

		log.info("주문번호" + vo.getOrd_code());

		// 1)주문테이블
		orderMapper.order_insert(vo);

		log.info("주문번호" + vo.getOrd_code());

	}

}
