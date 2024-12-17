package com.ezen.massagemall.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@Getter
public class EmailDTO {

	private String senderName; // 발신자이름
	private String senderMail; // 발신자 메일주소
	private String receiverMail; // 수신자 메일주소
	private String subject; // 제목
	private String message;

	public EmailDTO() {
		this.senderMail = "MassageMall";
		this.senderName = "MassageMall";
		this.subject = "MassageMall 회원가입 메일인증코드입니다.";
		this.message = "메일인증코드를 확인하세요";
	}
}
