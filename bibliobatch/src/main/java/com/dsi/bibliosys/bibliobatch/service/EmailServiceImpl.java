package com.dsi.bibliosys.bibliobatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("EmailService")
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Value("${spring.mail.from}")
	private String from;

	@Override
	public SimpleMailMessage sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
		return message;
	}
	
	@Scheduled(fixedRate = 1000)
	public void scheduleFixedRateTask() {
		sendSimpleMessage("someone@localhost", "Test email", "Texte de l'email");
	}

}
