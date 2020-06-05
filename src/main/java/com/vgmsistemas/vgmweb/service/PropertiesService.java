package com.vgmsistemas.vgmweb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {
	@Value("${contact.apikey}")
	private String apiKeyGoogle;

	public String getApiKeyGoogle() {
		return apiKeyGoogle;
	}

	public void setApiKeyGoogle(String apiKeyGoogle) {
		this.apiKeyGoogle = apiKeyGoogle;
	}
}
