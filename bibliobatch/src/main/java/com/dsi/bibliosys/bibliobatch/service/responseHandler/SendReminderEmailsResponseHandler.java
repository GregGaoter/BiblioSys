package com.dsi.bibliosys.bibliobatch.service.responseHandler;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SendReminderEmailsResponseHandler {

	public static HttpClientResponseHandler<List<String>> create() {
		return new HttpClientResponseHandler<>() {

			@Override
			public List<String> handleResponse(ClassicHttpResponse response) throws IOException {
				ObjectMapper responseMapper = new ObjectMapper();
				responseMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

				final int status = response.getCode();

				if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
					final HttpEntity responseEntity = response.getEntity();

					try {
						if (Objects.nonNull(responseEntity)) {
							String responseString = EntityUtils.toString(responseEntity);

							List<String> responseDto = responseMapper.readValue(responseString,
									new TypeReference<List<String>>() {
									});

							return responseDto;
						} else {
							return null;
						}
					} catch (ParseException e) {
						throw new ClientProtocolException(e);
					}
				} else {
					throw new ClientProtocolException(String.format(
							"Reminder emails request error. Unexpected response status: %d. Reason phrase: %s", status,
							response.getReasonPhrase()));
				}
			}
		};
	}

}
