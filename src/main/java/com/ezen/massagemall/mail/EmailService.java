package com.ezen.massagemall.mail;

import java.util.Random;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService {

	// Gmail SMTP서버정보를 가지고 있다.
	private final JavaMailSender mailSender;

	// 메일 내용에 타임리프를 사용하고 싶은 경우
	private final SpringTemplateEngine templateEngine;

	// type(메일발송 용도)
	public void sendMail(String type, EmailDTO dto, String message) {

		// 메일구성정보를 관리
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

			// 메일받는주소
			mimeMessageHelper.setTo(dto.getReceiverMail());
			// 메일보내는주소및이름
			mimeMessageHelper.setFrom(new InternetAddress(dto.getSenderMail(), dto.getSenderName()));
			// 메일제목
			mimeMessageHelper.setSubject(dto.getSubject());
			// 메일본문내용
			mimeMessageHelper.setText(setContext(message, type), true);
			// 메일 발송기능
			mailSender.send(mimeMessage);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// 인증코드 및 임시비밀번호 생성
	public String createAuthCode() {
		Random random = new Random();
		StringBuffer key = new StringBuffer();

		for (int i = 0; i < 8; i++) {
			int index = random.nextInt(4);

			switch (index) {
			case 0:
				key.append((char) ((int) random.nextInt(26) + 97));
				break; // 영문 소문자
			case 1:
				key.append((char) ((int) random.nextInt(26) + 65));
				break; // 영문 대문자
			default:
				key.append(random.nextInt(9)); // 숫자(0~8)
			}

		}
		return key.toString();
	}

	// 메일 템플릿사용(thymeleaf 사용)
	public String setContext(String message, String type) {
		Context context = new Context();

		// 타임리프페이지 "message" 이름으로 데이타를 전달
		context.setVariable("message", message);
		// type : "authcode" -> authcode.html
		return templateEngine.process(type, context); // 서버에서 타임리프 파일이 실행되어 html code결과가 생성된다.
	}

}
