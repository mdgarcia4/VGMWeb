package com.vgmsistemas.vgmweb.service.rest;

import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.vgmsistemas.vgmweb.service.PropertiesService;
import com.vgmsistemas.vgmweb.util.CodeResult;
import com.vgmsistemas.vgmweb.util.RutasServicios;


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
	static Logger logger = LoggerFactory.getLogger(GenericWs.class);    
    
    
    PropertiesService propertyService;
    
    public GenericWs(){  }
    
    public synchronized void refreshToken()throws Exception {
        
        try {       	
    		HttpResponse<JsonNode>  response = Unirest.post(url + RutasServicios.SERVICE_AUTORIZATION)
    	            .header("Authorization", AUTORIZATION)
    	            .header("Accept","application/json")
    	            .field("grant_type", "password")
    	            .field("username", "vgm")
    	            .field("password", "abc123")
    	            .asJson();
    		if (response.getStatus() == 200 )
    		{
	    		token = response.getBody().getObject().getString("access_token").toString(); 
	    		expiraEn = response.getBody().getObject().getInt("expires_in");  
	    		
	    		/*Asigno los resultados*/
	    		//propertyService.setKeyToken(token);
	    		propertyService.setExpiresIn(expiraEn);
    		}else
    		{
    			//TODO escribir en el log luego retornar valor para ser tratado
    			//String sError = CodeResult.getHttpError(response.getStatus());
    			token = "";
    		}
    		
		}catch(Exception ex) {
			logger.error("Error inesperado en clase GenericWs-Met: refreshToken.");
			throw ex;
		}
    }
    
    protected synchronized int callWebService
	    (String url, String webService, final Map<String,String> parametros, String volumnenDeDatos)
	    throws Exception {
    	return callWebService(url, webService, parametros, volumnenDeDatos, null);
	}
    
    protected synchronized int callWebService
	(String url, String webService, final Map<String,String> parametros, String volumnenDeDatos, final JSONObject body)
			throws Exception {
    	try {
    		if (volumnenDeDatos.equals(VOLUMEN_DE_DATOS_ALTO)) {
    	        timeOut = propertyService.getTimeOutAlto();
    	    } else if (volumnenDeDatos.equals(VOLUMEN_DE_DATOS_MEDIO)) {
    	        timeOut = propertyService.getTimeOutMedio();
    	    } else if (volumnenDeDatos.equals(VOLUMEN_DE_DATOS_BAJO)) {
    	        timeOut = propertyService.getTimeOutBajo();
    	    }

    	    //refreshToken();
    	    token = propertyService.getKeyToken();
    	    if (token.equals("")){ refreshToken();}
    	    
    	    /*asigno los valores*/
    	    HttpResponse<String> response = Unirest.post(url + webService)
    	    		.header("Accept", "application/json")
    	  		    .header("Content-Type", "application/x-www-form-urlencoded")
    	    		.field("access_token",token.toString())
    	    	    .field("ventaString", parametros.get("pedido"))
    	    	    .asString();
    	    if (response.getStatus() == 200 )
    		{
    	    	return CodeResult.RESULT_OK;
    		}else
    		{
    			//TODO escribir en el log luego retornar valor para ser tratado
    			//String sError = "StatusCode:" + String.valueOf(response.getStatus())+"-"+ CodeResult.getHttpError(response.getStatus());
    			return response.getStatus();
    		}
		} catch (Exception e) {
			logger.error("Error inesperado en clase GenericWs-Met: callWebService.");
			throw e;
		}
	    
    }

}
