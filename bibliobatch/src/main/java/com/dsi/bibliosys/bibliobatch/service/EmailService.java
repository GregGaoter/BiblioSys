package com.dsi.bibliosys.bibliobatch.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	public SimpleMailMessage sendReminderEmail(String to);

}
