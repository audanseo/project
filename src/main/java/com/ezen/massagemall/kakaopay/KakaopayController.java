package com.ezen.massagemall.kakaopay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/kakao/*")
@Slf4j
@RequiredArgsConstructor
@Controller
public class KakaopayController {

	private final KakaopayService kakaopayService;
//
//	@PostMapping("/kakaopay")
//	public ResponseEntity<ReadyResponse> kakaopay(OrderVO vo, HttpSession session) {
//
//		log.info("주문정보" + vo);
//
//		ResponseEntity<ReadyResponse> entity = null;
//		return entity;
//	}
}
