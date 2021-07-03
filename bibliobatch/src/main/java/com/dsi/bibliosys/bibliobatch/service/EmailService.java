package com.dsi.bibliosys.bibliobatch.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	public SimpleMailMessage sendSimpleMessage(String to, String subject, String text);

}
