package com.vgmsistemas.vgmweb.service.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.vgmsistemas.vgmweb.service.PropertiesService;
import com.vgmsistemas.vgmweb.util.RutasServicios;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public abstract class GenericWs {
    protected String url;
    protected static final String AUTORIZATION = "Basic " + "dmdtcHJldmVudGE6cHJldmVudGEq";
    protected String token = "";
    protected int expiraEn = 0;
    protected int timeOut = 200000; /*En milisegundos*/
    
    protected Integer metodo;
    protected static final String VOLUMEN_DE_DATOS_ALTO = "ALTO";
    protected static final String VOLUMEN_DE_DATOS_MEDIO = "MEDIO";
    protected static final String VOLUMEN_DE_DATOS_BAJO = "BAJO";

    protected static final int NO_REINTENTAR = 0;
    protected final String tag = "genericRequest";
    protected final String tagToken = "token";
    public static final String KEY_TOKEN = "token";
    protected static final String TOKEN_INVALIDO = "401";
    public static final String PREFERENCIA = "preferencia_token";

    
    
    @Autowired
	PropertiesService propertyService;
    
    public GenericWs(){
    	url = propertyService.getUrlServicio();
    }
    
    public synchronized void refreshToken()throws Exception {
        
        try {       	
    		HttpResponse<JsonNode>  response = Unirest.post(url + RutasServicios.SERVICE_AUTORIZATION)
    	            .header("Authorization", AUTORIZATION)
    	            .header("Accept","application/json")
    	            .field("grant_type", "password")
    	            .field("username", "vgm")
    	            .field("password", "abc123")
    	            .asJson();
    		token = response.getBody().getObject().getString("access_token").toString(); 
    		expiraEn = Integer.parseInt(response.getBody().getObject().getString("expires_in").toString());  
    		
    		/*Asigno los resultados*/
    		propertyService.setKeyToken(token);
    		propertyService.setExpiresIn(expiraEn);
    		
		}catch(Exception ex) {
			ex.getStackTrace();
		}
    }
}
