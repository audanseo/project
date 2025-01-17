package com.ezen.massagemall.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.massagemall.admin.utils.FileUtils;
import com.ezen.massagemall.cart.CartService;
import com.ezen.massagemall.cart.CartVO;
import com.ezen.massagemall.user.UserService;
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
	private final FileUtils fileUtils;
	private final UserService userService;

	@Value("${com.ezen.upload.path}")
	private String uploadPath;

	@GetMapping("order_info")
	public void order_info(CartVO vo, String type, HttpSession session, Model model) throws Exception {
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		vo.setMc_email(mc_email);
		if (type.equals("buy"))
			cartService.cart_add(vo);

		List<Map<String, Object>> cartDetails = cartService.getCartDetailsByUserId(mc_email);
		cartDetails.forEach(cartVO -> {
			cartVO.put("pro_upfolder", cartVO.get("pro_upfolder").toString().replace("\\", "/"));
		});

		String item_name = "";
		if (cartDetails.size() == 1) {
			item_name = (String) cartDetails.get(0).get("pro_name");
		} else {
			item_name = (String) cartDetails.get(0).get("pro_name") + "외" + cartDetails.size();
		}

		model.addAttribute("item_name", item_name);
		model.addAttribute("quantity", cartDetails.size());
		model.addAttribute("cartDetails", cartDetails);

		// 총주문금액
		model.addAttribute("order_total_price", cartService.getCartTotalPriceByUserId(mc_email));

		// 로그인한 사용자 정보
		UserVO userVO = userService.modify(mc_email);
		model.addAttribute("userVO", userVO);

	}

	@PostMapping("/order_save")
	public String order_save(OrderVO vo, HttpSession session, String p_method, String account_transfer, String sender,
			RedirectAttributes rttr) throws Exception {
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		vo.setMc_email(mc_email);

		String p_method_info = p_method + "/" + account_transfer + "/" + sender;

		orderService.order_process(vo, mc_email, p_method_info);

		rttr.addAttribute("ord_code", vo.getOrd_code());

		return "redirect:/order/order_result";
	}

	/*
	 * 2025-01-17 17:14:26,832 ERROR [jdbc.sqltiming] 121.
	 * PreparedStatement.execute() FAILED! insert into order_tbl (mc_email,
	 * ord_name, ord_zipcode, ord_addr, ord_deaddr, ord_phone, ord_price,
	 * ord_status)
	 * values('audanseo15@gmail.com','명문대',NULL,NULL,NULL,'010-1234-1234',0,NULL)
	 * {FAILED after 27 msec}
	 */
	@GetMapping("order_result")
	public void order_result(Integer ord_code, Model model) throws Exception {

		log.info("ord_code" + ord_code);
	}

	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {

		return fileUtils.getFile(uploadPath + "\\" + dateFolderName, fileName);
	}

}
