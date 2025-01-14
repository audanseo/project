package com.ezen.massagemall.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

		model.addAttribute("cartDetails", cartDetails);

		UserVO userVO = userService.modify(mc_email);
		model.addAttribute("userVO", userVO);

	}

	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {

		return fileUtils.getFile(uploadPath + "\\" + dateFolderName, fileName);
	}

}
