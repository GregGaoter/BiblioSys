package com.dsi.bibliosys.bibliobatch.service.responseHandler;

import java.io.IOException;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UpdatePretsResponseHandler {

	public static HttpClientResponseHandler<Boolean> create() {
		return new HttpClientResponseHandler<>() {

			@Override
			public Boolean handleResponse(ClassicHttpResponse response) throws IOException {
				ObjectMapper mapper = new ObjectMapper();
				final int status = response.getCode();
				if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
					final HttpEntity responseEntity = response.getEntity();
					if (responseEntity != null) {
						return mapper.readValue(responseEntity.getContent(), Boolean.class);
					} else {
						return null;
					}
				} else {
					throw new ClientProtocolException("Statut de réponse inattendu: " + status);
				}
			}
		};
	}

}
