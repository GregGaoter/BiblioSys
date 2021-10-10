package com.dsi.bibliosys.bibliobatch.service;

import java.io.IOException;
import java.util.List;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.springframework.stereotype.Service;

import com.dsi.bibliosys.bibliobatch.data.dto.PretDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReminderEmailsService {

	public void processSendingReminderEmails() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {

			final HttpGet pretHttpGet = new HttpGet("http://localhost:8080/pret/all");
			final HttpGet bibliothequeHttpGet = new HttpGet("http://localhost:8080/bibliotheque/all");

			final HttpClientResponseHandler<List<PretDto>> pretResponseHandler = new HttpClientResponseHandler<>() {

				@Override
				public List<PretDto> handleResponse(ClassicHttpResponse response)
						throws JsonProcessingException, ClientProtocolException {
					final int status = response.getCode();
					if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
						final HttpEntity pretEntity = response.getEntity();
						if (pretEntity != null) {
							String pretsJsonArray = mapper.writeValueAsString(response.getEntity());
							return mapper.readValue(pretsJsonArray, new TypeReference<List<PretDto>>() {
							});
						} else {
							return null;
						}
					} else {
						throw new ClientProtocolException("Statut de réponse inattendu: " + status);
					}
				}
			};
			
			List<PretDto> prets = httpClient.execute(bibliothequeHttpGet, pretResponseHandler);
		}
	}

}
