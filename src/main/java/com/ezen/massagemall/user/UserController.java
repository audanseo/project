package com.ezen.massagemall.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.massagemall.mail.EmailDTO;
import com.ezen.massagemall.mail.EmailService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/user/*")
@Slf4j
@Controller
public class UserController {

	private final EmailService emailService;
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

	// 이메일(아이디체크)
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

	// 닉네임 (별명체크)
	@GetMapping("/nicknamecheck")
	public ResponseEntity<String> nicknamecheck(String mc_nickname) throws Exception {
		ResponseEntity<String> entity = null;

		String isUse = "";
		if (userService.emailcheck(mc_nickname) != null) {
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

	// 회원수정
	@GetMapping("/modify")
	public void modify(HttpSession session, Model model) throws Exception {
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		// log.info("modify 호출");
		UserVO userVO = userService.modify(mc_email);
		model.addAttribute("userVO", userVO);
	}

	@PostMapping("/modify")
	public String modify(UserVO vo) throws Exception {
		userService.modify_save(vo);
		return "redirect:/";
	}

	@GetMapping("/mypage")
	public void mypage() throws Exception {

	}

	// 비밀번호 변경
	@GetMapping("/pwchange")
	public void pwchange() throws Exception {
	}

	@PostMapping("/pwchange")
	public String pwchange(@RequestParam("cur_password") String mc_password, String new_password, HttpSession session,
			RedirectAttributes rttr) throws Exception {

		String url = "";
		String msg = "";

		String db_password = ((UserVO) session.getAttribute("login_auth")).getMc_password();
		String mc_email = ((UserVO) session.getAttribute("login_auth")).getMc_email();
		if (passwordEncoder.matches(mc_password, db_password)) {
			String encode_new_password = passwordEncoder.encode(new_password);
			userService.pwchange(mc_email, encode_new_password);
			url = "/";
			msg = "success";
		} else {
			url = "/user/pwchange";
			msg = "fail";
		}
		rttr.addFlashAttribute("msg", msg);
		return "redirect:" + url;
	}

	// 아이디 찾기 폼
	@GetMapping("/lostpass")
	public String lostpass() throws Exception {
		return "/user/lostpass";
	}

	@GetMapping("/emailsearch")
	public ResponseEntity<String> emailsearch(String mc_name, String mc_phone) throws Exception {
		ResponseEntity<String> entity = null;
		String result = "";

		String mc_email = userService.emailsearch(mc_name, mc_phone);
		if (mc_email != null) {
			result = mc_email;
		} else {
			result = "fail";
		}
		entity = new ResponseEntity<String>(result, HttpStatus.OK);
		return entity;
	}

	// 암호화가 단방향이다보니 찾아서 로그인을 하려는데 로그인을 할 수가 없음
//	@GetMapping("/passwordsearch") 
//	public ResponseEntity<String> passwordsearch(String mc_email, String mc_name) throws Exception {
//		ResponseEntity<String> entity = null;
//		String result = "";
//
//		String mc_password = userService.passwordsearch(mc_email, mc_name);
//		if (mc_password != null) {
//			result = mc_password;
//		} else {
//			result = "fail";
//		}
//		entity = new ResponseEntity<String>(result, HttpStatus.OK);
//		return entity;
//	}

	// 임시비밀번호 -메일발송
	@GetMapping("passwordsearch")
	public ResponseEntity<String> passwordsearch(String mc_email, String mc_name) throws Exception {
		ResponseEntity<String> entity = null;
		String result = "";

		String d_u_email = userService.passwordsearch(mc_email, mc_name);

		if (d_u_email != null) {
			result = "success";

			String imsi_pw = emailService.createAuthCode();
			userService.pwchange(mc_email, passwordEncoder.encode(imsi_pw));

			String type = "mail/passwordsearch";
			EmailDTO dto = new EmailDTO();
			dto.setReceiverMail(d_u_email);
			dto.setSubject("Massage Mall 임시비밀번호를 보냅니다.");
			emailService.sendMail(type, dto, imsi_pw);
		} else {
			result = "fail";
		}
		entity = new ResponseEntity<String>(result, HttpStatus.OK);
		return entity;
	}

}
