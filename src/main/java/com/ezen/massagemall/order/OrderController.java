package com.ezen.massagemall.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.massagemall.cart.CartService;
import com.ezen.massagemall.cart.CartVO;
import com.ezen.massagemall.user.UserVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/order/*")
@RequiredArgsConstructor
@Slf4j
@Controller
public class OrderController {

	private final OrderService orderService;
	private final CartService cartService;

	@GetMapping("order_info")
	public void order_info(CartVO vo, String type, HttpSession session) throws Exception {
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		vo.setMc_email(mc_email);
		if (type.equals("buy"))
			cartService.cart_add(vo);

	}

}
