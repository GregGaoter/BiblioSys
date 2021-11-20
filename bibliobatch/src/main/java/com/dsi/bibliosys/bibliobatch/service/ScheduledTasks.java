package com.dsi.bibliosys.bibliobatch.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	
	@Autowired
	private ReminderEmailsService reminderEmailsService;
	
	@Scheduled(cron = "00 10 19 * * ?")
	public void sendReminderEmails() throws IOException {
		reminderEmailsService.processSendingReminderEmails();
	}

}
