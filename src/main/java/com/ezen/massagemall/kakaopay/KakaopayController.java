package com.ezen.massagemall.kakaopay;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.massagemall.order.OrderService;
import com.ezen.massagemall.order.OrderVO;
import com.ezen.massagemall.user.UserVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/kakao/*")
@Slf4j
@RequiredArgsConstructor
@Controller
public class KakaopayController {

	private final KakaopayService kakaopayService;
	private final OrderService orderService;

	private OrderVO order_info;
	private String mc_email;
	private int order_total_price;

	// 카카오페이 api에서 post사용을 원함
	@PostMapping("/kakaoPay")
	public ResponseEntity<ReadyResponse> kakaopay(OrderVO vo, String item_name, Integer quantity, HttpSession session) {

		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		vo.setMc_email(mc_email);

		log.info("주문정보" + vo);

		this.order_info = vo;
		this.order_total_price = vo.getOrd_price();
		ResponseEntity<ReadyResponse> entity = null;

		String partner_order_id = "massagemall[" + mc_email + "] -" + new Date().toString();

		ReadyResponse readyResponse = kakaopayService.ready(partner_order_id, mc_email, item_name, quantity,
				order_total_price, 0);
		log.info("결제준비요청 응답결과" + readyResponse.toString());

		entity = new ResponseEntity<ReadyResponse>(readyResponse, HttpStatus.OK);
		return entity;
	}

	// 카카오페이API서버 호출
	@GetMapping("/approval")
	public String approval(String pg_token, RedirectAttributes rttr) {

		log.info("pg_token" + pg_token);
		// 결제승인요청
		String response = kakaopayService.approve(pg_token);

		// 결제 승인요청의 성공 응답파라미터로 aid를 확인
		if (response.contains("aid")) {

			// 주문 관련 작업
			orderService.order_process(this.order_info, mc_email, "카카오페이");
		}

		rttr.addAttribute("ord_code", order_info.getOrd_code());

		return "redirect:/order/order_result";
	}

	// 결제취소
	@GetMapping("/cancel")
	public String cancel() {
		return "/order/order_cancel";
	}

	// 결제실패
	@GetMapping("/fail")
	public String fail() {
		return "/order/order_fail";
	}
}
