package com.vgmsistemas.vgmweb.util;

import java.util.HashMap;
import java.util.Map;

public class CodeResult {
	/*Errores de app*/
    public static final int RESULT_OK = 0;
	public static final int RESULT_ERROR = 1;
	public static final int RESULT_MONTO_DISPONIBLE_CERO_RECIBO_BO = 2;	
	public static final int RESULT_NO_SE_ENCONTRO_UN_COMPROBANTE_EN_CTACTE_RECIBO_BO = 3;
	public static final int RESULT_ALGUN_COMPROBANTE_TIENE_SALDO_CERO = 4;
	public static final int RESULT_VENDEDOR_INVALID = 101;
	public static final int RESULT_PEDIDOS_NO_AUTORIZADO = 102;
	public static final int RESULT_RECIBO_YA_IMPUTADO = 103;
	public static final int RESULT_PEDIDO_EXISTENTE = 104; // lo dejo para posterior uso.
	
	/*Errores de Http*/
	//El request es correcto. Esta es la respuesta estándar para respuestas correctas.
	public static final int HTTP_OK = 200;
	//201 Created. El request se ha completado y se ha creado un nuevo recurso.
	public static final int HTTP_CREATED = 201;
	//202 Aceptada. El request se ha aceptado para procesarlo, pero el proceso aún no ha terminado.
	public static final int HTTP_ACEPTADA = 202;
	//203 Non-Authoritative Information. El request se ha procesado correctamente, pero devuelve información que podría venir de otra fuente.
	public static final int HTTP_NON_AUTHORITATIVE = 203;
	//204 No Content. El request se ha procesado correctamente, pero no devuelve ningún contenido.
	public static final int HTTP_NO_CONTENT = 204;
	//205 Reset Content. El request se ha procesado correctamente, pero no devuelve ningún contenido y se requiere que el requester recargue el contenido.
	public static final int HTTP_RESET_CONTENT = 205;
	//206 Partial Content. El servidor devuelve sólo parte del recurso debido a una limitación que ha configurado el cliente (se usa en herramientas de descarga como wget).
	public static final int HTTP_PARTIAL_CONTENT = 206;
	//207 Multi-Status (WebDAV; RFC 4918).El cuerpo del mensaje es XML y puede contener un número de códigos de estado diferentes dependiendo del número de sub-requests.
	public static final int HTTP_MULTI_STATUS = 207;
	
	//300 Multiple Choices. Es una lista de enlaces. El usuario puede seleccionar un enlace e ir a esa dirección. Hay un máximo de cinco direcciones.
	public static final int HTTP_MULTIPLE_CHOICE = 300;
	//301 Moved Permanently. La página solicitada se ha movido permanentemente a una nueva URI.
	public static final int HTTP_MOVE_PERMANENTLY = 301;
	//302 Found. La página solicitada se ha movido temporalmente a una nueva URI.
	public static final int HTTP_FOUND = 302;
	//303 See Other. La página solicitada se puede encontrar en una URI diferente.
	public static final int HTTP_SEE_OTHER = 303;
	//304 Not Modified. Indica que la página solicitada no se ha modificado desde la última petición.
	public static final int HTTP_NOT_MODIFIED = 304;
	//305 Use Proxy (desde HTTP/1.1). El recurso solicitado sólo está disponible a través de proxy, cuya dirección se proporciona en la respuesta. Muchos clientes HTTP como Mozilla o Internet Explorer no manejan bien estas respuestas con estos códigos de estado, sobre todo por seguridad.
	public static final int HTTP_USE_PROXY = 305;
	//307 Temporary Redirect (desde HTTP/1.1). La página solicitada se ha movido temporalmente a otra URL. En este caso el recurso debería de repetirse con otra URI, sin embargo, futuros requests deberán usar la URI original. Al contrario que con la 302, el método request no puede cambiar cuando se reubique el request original.
	public static final int HTTP_TEMPORARY_REDIRECT = 307;
	//308 Permanent Redirect (RFC 7538). El request y futuros requests deberían repetirse usando otro URI. Este también es similar al 301, pero no permite al método HTTP que cambie.
	public static final int HTTP_PERMANENT_REDIRECT = 308;
	
	//400 Bad Request. El servidor no puede o no va a procesar el request por un error de sintaxis del cliente.
	public static final int HTTP_BAD_REQUEST = 400;
	//401 Unauthorized (RFC 7235). Similar al error 403, pero se usa cuando se requiere una autentificación y ha fallado o todavía no se a facilitado.
	public static final int HTTP_UNAUTHORIZED = 401;
	//402 Payment Required. Reservado para futuro uso. La intención original fue para pago con tarjeta o micropago, pero eso no ha ocurrido, y este código apenas se usa.
	public static final int HTTP_PAYMENT_REQUIRED = 402;
	//403 Forbidden. El request fue válido pero el servidor se niega a responder.
	public static final int HTTP_FORBIDDEN = 403;
	//404 Not Found. El recurso del request no se ha podido encontrar pero podría estar disponible en el futuro. Se permiten requests subsecuentes por parte del cliente.
	public static final int HTTP_NOT_FOUND = 404;
	//405 Method Not Allowed. Se ha hecho un request con un recurso usando un método request no soportado por ese recurso (por ejemplo usando GET en un formulario que requiere POST).
	public static final int HTTP_METOD_NOT_ALLOWED = 405;
	//406 Not Acceptable. El recurso solicitado solo genera contenido no aceptado de acuerdo con los headers Accept enviados en el request.
	public static final int HTTP_NOT_ACCEPTABLE = 406;
	//407 Proxy Authentication Required (RFC 7235). El cliente se debe identificar primero con el proxy.
	public static final int HTTP_PROXY_AUTHENTICATIN = 407;
	//408 Request Timeout. El cliente no ha enviado un request con el tiempo necesario con el que el servidor estaba preparado para esperar. El cliente podría repetir el request sin modificaciones más tarde.
	public static final int HTTP_REQUEST_TIMEOUT = 408;
	//409 Conflict. Conflicto en el request, como cuando se actualizan al mismo tiempo dos recursos.
	public static final int HTTP_CONFLIC = 409;
	//410 Gone. El recurso solicitado no está disponible ni lo estará en el futuro. Un buscador eliminará antes una página 410 que una 404.
	public static final int HTTP_GONE = 410;
	//411 Length Required. El request no especificó la longitud del contenido, la cual es requerida por el recurso solicitado.
	public static final int HTTP_LENGTH_REQUIRED = 411;
	//412 Precondition Failed (RFC 7232). El servidor no cumple una de las precondiciones que el requester añade en el request.
	public static final int HTTP_PRECONDITION_FAILED = 412;
	//413 Request Entity Too Large. El request es más largo que el que está dispuesto a aceptar el servidor.
	public static final int HTPP_REQUEST_ENTITY_TOO_LARGE = 413;
	//414 Request-URI Too Long. El URI es muy largo para que el servidor lo procese.
	public static final int HTTP_REQUEST_URI_TOO_LONG = 414;
	//415 Unsupported Media Type. La entidad request tiene un media type que el servidor o recurso no soportan.
	public static final int HTTP_UNSUPPORTED_MEDIA_TYPE = 415;
	//416 Requested Range Not Satisfiable (RFC 7233). El cliente ha solicitado una porción de archivo, pero el servidor no puede ofrecer esa porción.
	public static final int HTTP_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
	//417 Expectation Failed. El servidor no puede cumplir los requerimientos del header del request Expect.
	public static final int HTPP_EXPECTATION_FAILED = 417;
	
	//500 Internal Server Error. Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.
	public static final int HTPP_INTERNAL_SERVER_ERROR = 500;
	//501 Not Implemented. El servidor o no reconoce el método del request o carece de la capacidad para completarlo. Normalmente es algo que se ofrecerá en el futuro, como un nuevo servicio de una API.
	public static final int HTPP_NOT_IMPLEMENTED = 501;
	//502 Bad Gateway. El server actuaba como puerta de entrada o proxy y recibió una respuesta inválida del servidor upstream.
	public static final int HTTP_BAD_GETEWAY = 502;
	//503 Service Unavailable. El servidor está actualmente no disponible, ya sea por mantenimiento o por sobrecarga.
	public static final int HTTP_SERVICE_UNAVIALEBLE = 503;
	//504 Gateway Timeout. El servidor estaba actuando como puerta de entrada o proxy y no recibió una respuesta oportuna por parte del servidor upstream.
	public static final int HTPP_GATEWAY_TIMEOUT = 504;
	//505 HTTP Version Not Supported. El servidor no soporta la versión del protocolo HTTP usada en el request.
	public static final int HTTP_VERSION_NOT_SUPPORTED = 505;
	//511 Network Authentication Required (RFC 6585). El cliente necesita autentificarse para poder acceder a la red.	
	public static final int HTTP_NETWORK_AUTHENTICATION = 506;
	
	private static final Map<Integer,String> httpError = new HashMap<Integer,String>();
	
	public static String getHttpError(int code) {
		if(httpError.isEmpty()) 
		{
			httpError.put(HTTP_OK,"El request es correcto. Esta es la respuesta estándar para respuestas correctas.");
			httpError.put(HTTP_CREATED,	"El request se ha completado y se ha creado un nuevo recurso.");
			httpError.put(HTTP_ACEPTADA,"El request se ha aceptado para procesarlo, pero el proceso aún no ha terminado.");
			httpError.put(HTTP_NON_AUTHORITATIVE,"El request se ha procesado correctamente, pero devuelve información que podría venir de otra fuente.");
			httpError.put(HTTP_NO_CONTENT , "El request se ha procesado correctamente, pero no devuelve ningún contenido.");
			httpError.put(HTTP_RESET_CONTENT , "El request se ha procesado correctamente, pero no devuelve ningún contenido y se requiere que el requester recargue el contenido.");
			httpError.put(HTTP_PARTIAL_CONTENT , "El servidor devuelve sólo parte del recurso debido a una limitación que ha configurado el cliente (se usa en herramientas de descarga como wget).");
			httpError.put(HTTP_MULTI_STATUS , "El cuerpo del mensaje es XML y puede contener un número de códigos de estado diferentes dependiendo del número de sub-requests.");
			httpError.put(HTTP_MULTIPLE_CHOICE , "Es una lista de enlaces. El usuario puede seleccionar un enlace e ir a esa dirección. Hay un máximo de cinco direcciones.");
			httpError.put(HTTP_MOVE_PERMANENTLY , "La página solicitada se ha movido permanentemente a una nueva URI.");
			httpError.put(HTTP_FOUND , "La página solicitada se ha movido temporalmente a una nueva URI.");
			httpError.put(HTTP_SEE_OTHER , "La página solicitada se puede encontrar en una URI diferente.");
			httpError.put(HTTP_NOT_MODIFIED , "Indica que la página solicitada no se ha modificado desde la última petición.");
			httpError.put(HTTP_USE_PROXY , "El recurso solicitado sólo está disponible a través de proxy, cuya dirección se proporciona en la respuesta.");
			httpError.put(HTTP_TEMPORARY_REDIRECT , "La página solicitada se ha movido temporalmente a otra URL.");
			httpError.put(HTTP_PERMANENT_REDIRECT , "El request y futuros requests deberían repetirse usando otro URI.");
			httpError.put(HTTP_BAD_REQUEST , "El servidor no puede o no va a procesar el request por un error de sintaxis del cliente.");
			httpError.put(HTTP_UNAUTHORIZED , "El request fue válido pero el servidor se niega a responder, se requiere una autentificación y ha fallado o todavía no se a facilitado.");
			httpError.put(HTTP_PAYMENT_REQUIRED , "Reservado para futuro uso.");
			httpError.put(HTTP_FORBIDDEN , "El request fue válido pero el servidor se niega a responder.");
			httpError.put(HTTP_NOT_FOUND , "El recurso del request no se ha podido encontrar pero podría estar disponible en el futuro.");
			httpError.put(HTTP_METOD_NOT_ALLOWED , "Se ha hecho un request con un recurso usando un método request no soportado por ese recurso");
			httpError.put(HTTP_NOT_ACCEPTABLE , "El recurso solicitado solo genera contenido no aceptado de acuerdo con los headers Accept enviados en el request.");
			httpError.put(HTTP_PROXY_AUTHENTICATIN , "El cliente se debe identificar primero con el proxy.");
			httpError.put(HTTP_REQUEST_TIMEOUT , "El cliente no ha enviado un request con el tiempo necesario con el que el servidor estaba preparado para esperar.");
			httpError.put(HTTP_CONFLIC , "Conflicto en el request, como cuando se actualizan al mismo tiempo dos recursos.");
			httpError.put(HTTP_GONE , "El recurso solicitado no está disponible ni lo estará en el futuro.");
			httpError.put(HTTP_LENGTH_REQUIRED , "El request no especificó la longitud del contenido, la cual es requerida por el recurso solicitado.");
			httpError.put(HTTP_PRECONDITION_FAILED , "El servidor no cumple una de las precondiciones que el requester añade en el request.");
			httpError.put(HTPP_REQUEST_ENTITY_TOO_LARGE , "El request es más largo que el que está dispuesto a aceptar el servidor.");
			httpError.put(HTTP_REQUEST_URI_TOO_LONG , "El URI es muy largo para que el servidor lo procese.");
			httpError.put(HTTP_UNSUPPORTED_MEDIA_TYPE , "La entidad request tiene un media type que el servidor o recurso no soportan.");
			httpError.put(HTTP_REQUESTED_RANGE_NOT_SATISFIABLE , "El cliente ha solicitado una porción de archivo, pero el servidor no puede ofrecer esa porción.");
			httpError.put(HTPP_EXPECTATION_FAILED , "El servidor no puede cumplir los requerimientos del header del request Expect.");
			httpError.put(HTPP_INTERNAL_SERVER_ERROR , "Error genérico, cuando se ha dado una condición no esperada y no se puede concretar el mensaje.");
			httpError.put(HTPP_NOT_IMPLEMENTED , "El servidor o no reconoce el método del request o carece de la capacidad para completarlo.");
			httpError.put(HTTP_BAD_GETEWAY , "El server actuaba como puerta de entrada o proxy y recibió una respuesta inválida del servidor upstream.");
			httpError.put(HTTP_SERVICE_UNAVIALEBLE , "El servidor está actualmente no disponible, ya sea por mantenimiento o por sobrecarga.");
			httpError.put(HTPP_GATEWAY_TIMEOUT , "El servidor estaba actuando como puerta de entrada o proxy y no recibió una respuesta oportuna por parte del servidor upstream.");
			httpError.put(HTTP_VERSION_NOT_SUPPORTED , "El servidor no soporta la versión del protocolo HTTP usada en el request.");
			httpError.put(HTTP_NETWORK_AUTHENTICATION , "El cliente necesita autentificarse para poder acceder a la red.");			
		}
		return httpError.get(code)== null ? "Error inesperado": httpError.get(code);
	}
	
	
	
	
}
