package com.vgmsistemas.vgmweb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {
	@Value("${contact.apikey}")
	private String apiKeyGoogle;	
	
	@Value("${shopping-cart.urlservicio}")
	private String urlServicio;
	
	@Value("${shopping-cart.token}")
	private String keyToken;
	
	@Value("${shopping-cart.expires_in}")
	private int expiresIn;


	public String getApiKeyGoogle() {
		if(apiKeyGoogle == null) {
			return "";
		}else {
			return apiKeyGoogle;
		}
	}

	public void setApiKeyGoogle(String apiKeyGoogle) {
		this.apiKeyGoogle = apiKeyGoogle;
	}

	public String getUrlServicio() {
		if(urlServicio == null) {
			return "";
		}else {
			return urlServicio;
		}
	}

	public void setUrlServicio(String urlServicio) {
		this.urlServicio = urlServicio;
	}

	public String getKeyToken() {
		if(keyToken == null) {
			return "";
		}else {
			return keyToken;
		}
	}

	public void setKeyToken(String keyToken) {
		this.keyToken = keyToken;
	}

	public int getExpiresIn() {		
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
