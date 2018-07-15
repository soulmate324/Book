package com.anne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.mail.javamail.JavaMailSender;

import com.anne.service.MailService;

@RestController
public class MailController {
	@Autowired
	MailService mailService;

	@RequestMapping(value = "/test-mail-send")
	@ResponseBody
	public String testMailSend() {
		boolean isSend = mailService.sendMail("psme324@naver.com", "제목", "메일 내용이다. !!");

		if (isSend) {
			return "메일이 발송되었다!!";
		} else {
			return "메일 보내기 실패 : 로그 확인 바람.!!";
		}
	}
}