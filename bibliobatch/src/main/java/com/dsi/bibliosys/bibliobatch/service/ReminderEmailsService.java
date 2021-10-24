package com.dsi.bibliosys.bibliobatch.service;

import java.io.IOException;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.bibliobatch.service.responseHandler.SendReminderEmailsResponseHandler;
import com.dsi.bibliosys.bibliobatch.service.responseHandler.UpdatePretsResponseHandler;

@Service
public class ReminderEmailsService {

	@Value("${bibliosys.base-url}")
	private String baseUrl;

	@Autowired
	private EmailServiceImpl emailServiceImpl;

	public void processSendingReminderEmails() throws IOException {
		Boolean isPretsUpdated = updatePrets();
		if (isPretsUpdated) {
			List<String> emails = sendReminderEmails();
			emails.forEach(email -> emailServiceImpl.sendReminderEmail(email));
		}
	}

	private Boolean updatePrets() throws IOException {
		try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
			final HttpGet httpGet = new HttpGet(baseUrl.concat("/pret/update"));
			final HttpClientResponseHandler<Boolean> responseHandler = UpdatePretsResponseHandler.create();
			Boolean isPretsUpdated = httpClient.execute(httpGet, responseHandler);
			return isPretsUpdated;
		}
	}

	private List<String> sendReminderEmails() throws IOException {
		try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
			final HttpGet httpGet = new HttpGet(baseUrl.concat("/identifiant/pretExpired"));
			final HttpClientResponseHandler<List<String>> responseHandler = SendReminderEmailsResponseHandler.create();
			List<String> emails = httpClient.execute(httpGet, responseHandler);
			return emails;
		}
	}

}
