package com.vgmsistemas.vgmweb.service.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.vgmsistemas.vgmweb.dto.DailyOrderWsDto;
import com.vgmsistemas.vgmweb.service.PropertiesService;
import com.vgmsistemas.vgmweb.util.CodeResult;
import com.vgmsistemas.vgmweb.util.RutasServicios;

@Component
public class VentaWs extends GenericWs{
	
	@Autowired
    public VentaWs(PropertiesService property){ 
    	this.propertyService =  property;
    	
		url = propertyService.getUrlServicio(); 	
    }
	
	public int send(DailyOrderWsDto venta) throws Exception{
		String peticion = RutasServicios.SERVICE_DAILY_ORDER;
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZ").create();
		JsonObject jsonPedido = gson.toJsonTree(venta).getAsJsonObject();
		Map<String, String> parametros = new HashMap<>();
		parametros.put("pedido", jsonPedido.toString());
		int response = callWebService(url, peticion, parametros,VOLUMEN_DE_DATOS_MEDIO);
		
		try {
            int result = Integer.valueOf(response);
            return result;
        } catch (Exception e){
			logger.error("Error inesperado en clase VentaWs-Met: send.");
		    return CodeResult.RESULT_ERROR;
        }
	}
}
