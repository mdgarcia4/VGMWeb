package com.vgmsistemas.vgmweb.service.rest;

import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.dto.DailyOrderWsDto;

public class VentaWs extends GenericWs{
	
	public int send(DailyOrderWsDto venta) throws Exception{
		//String peticion = "/createPedido/";
		//String paramName = "pedido";
		//metodo = Request.Method.POST;

		//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZ").create();
		//JsonObject jsonPedido = gson.toJsonTree(venta).getAsJsonObject();
		//Map<String, String> parametros = new HashMap<>();
		//parametros.put("pedido", jsonPedido.toString());
		//String response = callWebService(url, webService+peticion, metodo, parametros,/*jsonPedido, paramName, */VOLUMEN_DE_DATOS_MEDIO);
		//CodeResult.checkCode(Integer.valueOf(response));
		//try {
         //   int result = Integer.valueOf(response);
        //    return result;
        //} catch (Exception e){
		//    return RESULT_ERROR;
        //}
		return 1;
	}
}
