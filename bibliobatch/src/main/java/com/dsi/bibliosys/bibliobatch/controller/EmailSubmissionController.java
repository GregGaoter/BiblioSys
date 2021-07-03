package com.dsi.bibliosys.bibliobatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsi.bibliosys.bibliobatch.service.EmailService;

@RestController
public class EmailSubmissionController {

	@Autowired
	private EmailService emailService;

	@RequestMapping("/email")
	public ResponseEntity<SimpleMailMessage> sendSimpleMailMessage() {
		return ResponseEntity.ok(emailService.sendSimpleMessage("someone@localhost", "Test email", "Texte de l'email"));
	}

}
