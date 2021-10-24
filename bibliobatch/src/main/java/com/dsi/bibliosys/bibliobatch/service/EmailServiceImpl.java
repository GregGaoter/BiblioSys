package com.dsi.bibliosys.bibliobatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
//@Service("EmailService")
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Value("${spring.mail.from}")
	private String from;

	@Override
	public SimpleMailMessage sendReminderEmail(String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("Sujet");
		message.setText("Message");
		emailSender.send(message);
		return message;
	}

}
