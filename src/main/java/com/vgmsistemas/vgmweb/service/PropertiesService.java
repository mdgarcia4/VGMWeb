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
	
	@Value("${webservicesrest.timeOutAlto}")
	private int timeOutAlto;
	
	@Value("${webservicesrest.timeOutMedio}")
	private int timeOutMedio;
	
	@Value("${webservicesrest.timeOutBajo}")
	private int timeOutBajo;
	
	@Value("${pagina.nameapp}")
	private String nameApp;

	@Value("${pagina.path.imagenes}")
	private String pathSrcImagen;
		
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
	
	public int getTimeOutAlto() {
		return timeOutAlto;
	}

	public void setTimeOutAlto(int timeOutAlto) {
		this.timeOutAlto = timeOutAlto;
	}

	public int getTimeOutMedio() {
		return timeOutMedio;
	}

	public void setTimeOutMedio(int timeOutMedio) {
		this.timeOutMedio = timeOutMedio;
	}

	public int getTimeOutBajo() {
		return timeOutBajo;
	}

	public void setTimeOutBajo(int timeOutBajo) {
		this.timeOutBajo = timeOutBajo;
	}
	
	public String getNameApp() {
		return nameApp;
	}

	public void setNameApp(String nameApp) {
		this.nameApp = nameApp;
	}
	public String getPathSrcImagen() {
		return pathSrcImagen;
	}

	public void setPathSrcImagen(String pathSrcImagen) {
		this.pathSrcImagen = pathSrcImagen;
	}
}
