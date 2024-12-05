package com.ezen.massagemall.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/user/*")
@Slf4j
@Controller
public class UserController {

	private final UserService userService;

	private final PasswordEncoder passwordEncoder;

	@GetMapping("/join")
	public void joinForm() {

	}

	// 회원가입
	@PostMapping("/join")
	public String join(UserVO vo) {
		vo.setMc_password(passwordEncoder.encode(vo.getMc_password()));
		userService.join(vo);
		return "redirect:/user/login";
	}

	@GetMapping("/emailcheck")
	public ResponseEntity<String> emailcheck(String mc_email) throws Exception {
		ResponseEntity<String> entity = null;

		String isUse = "";
		if (userService.emailcheck(mc_email) != null) {
			isUse = "no";
		} else {
			isUse = "yes";
		}
		entity = new ResponseEntity<String>(isUse, HttpStatus.OK);
		return entity;
	}

	// 로그인
	@GetMapping("/login")
	public void login() {

	}

	@PostMapping("/login")
	public String login(LoginDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {

		UserVO userVO = userService.login(dto.getMc_email());

		String url = "";
		String status = "";
		if (userVO != null) {
			if (passwordEncoder.matches(dto.getMc_password(), userVO.getMc_password())) {
				session.setAttribute("login_auth", userVO);
				url = "/";
			} else {
				status = "pwFail";
				url = "/user/login";
			}
		} else {
			status = "emailFail";
			url = "/user/login";
		}
		rttr.addFlashAttribute("status", status);

		return "redirect:" + url;
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/modify")
	public void modify(HttpSession session, Model model) throws Exception {
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		// log.info("modify 호출");
		UserVO userVO = userService.modify(mc_email);
		model.addAttribute("userVO", userVO);

	}
}
