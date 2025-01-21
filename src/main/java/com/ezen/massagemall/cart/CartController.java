package com.ezen.massagemall.cart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.massagemall.admin.utils.FileUtils;
import com.ezen.massagemall.user.UserVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/cart/*")
@Slf4j
@Controller
public class CartController {

	private final CartService cartService;
	private final FileUtils fileUtils;

	@Value("${com.ezen.upload.path}")
	private String uploadPath;

	@PostMapping("/cart_add")
	public ResponseEntity<String> cart_add(CartVO vo, HttpSession session) throws Exception {
		log.info("장바구니" + vo);
		ResponseEntity<String> entity = null;
		// 로그인 불러오기
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		vo.setMc_email(mc_email);

		cartService.cart_add(vo);
		entity = new ResponseEntity<String>("success", HttpStatus.OK);

		return entity;
	}

	@GetMapping("cart_list")
	public void cart_list(HttpSession session, Model model) throws Exception {
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();

		List<Map<String, Object>> cart_list = cartService.cart_list(mc_email);

		cart_list.forEach(cartVO -> {
			cartVO.put("pro_upfolder", cartVO.get("pro_upfolder").toString().replace("\\", "/"));
		});
		model.addAttribute("cart_list", cart_list);

		// 장바구니 비우기작업에서 총금액이 null로 발생
		model.addAttribute("cart_total_price", cartService.getCartTotalPriceByUserId(mc_email));
	}

	@GetMapping("/cart_change")
	public String cart_change(CartVO vo, HttpSession session) throws Exception {
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		vo.setMc_email(mc_email);

		cartService.cart_change(vo);

		return "redirect:/cart/cart_list";
	}

	@PostMapping("/cart_sel_delete")
	public String cart_sel_delete(int[] check, HttpSession session) throws Exception {
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();

		cartService.cart_sel_delete(check, mc_email);
		return "redirect:/cart/cart_list";
	}

	@GetMapping("/cart_empty")
	public String cart_empty(HttpSession session) throws Exception {
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();

		cartService.cart_empty(mc_email);
		return "redirect:/cart/cart_list";
	}

	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {

		return fileUtils.getFile(uploadPath + "\\" + dateFolderName, fileName);
	}

}
