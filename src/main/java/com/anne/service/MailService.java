package com.anne.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	Logger logger = LoggerFactory.getLogger(MailService.class);

	final String user = "lovebooks.manager@gmail.com";
	final String password = "";

	public boolean sendMail(final String to, final String subject, final String content) {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress("psme324@naver.com"));
			message.setSubject("제목");
			message.setText("내용");
			Transport.send(message);
			System.out.println("message sent successfully...");
			return true;
		} catch (MailException me) {
			logger.error("MailException", me);
			return false;
		} catch (Exception e) {
			logger.error("Exception", e);
			return false;
		}
	}
}