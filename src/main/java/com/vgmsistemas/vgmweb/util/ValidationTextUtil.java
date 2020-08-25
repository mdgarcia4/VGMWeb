package com.vgmsistemas.vgmweb.util;

import java.util.regex.*;

public class ValidationTextUtil {
	public static boolean isTelefonoValido(String telefonoValidar) {
		// compilamos el patron: numeros minimo 1 maximo 20;
		Pattern patron = Pattern.compile("^[0-9]{1,20}$");
		// creamos el Matcher a partir del patron, la cadena como parametro
		Matcher m = patron.matcher(telefonoValidar);

		return m.find() ? true : false;
	}
	public static boolean isDentroDelRango(String datoAValidar, int rangoMinimo, int rangoMaximo) {
		return (datoAValidar.length()>= rangoMinimo && datoAValidar.length() <= rangoMaximo); 
	}
	public static boolean isEmailValido(String datoAValidar) {
		// compilamos el patron
		Pattern patron = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
		// creamos el Matcher a partir del patron, la cadena como parametro
		Matcher m = patron.matcher(datoAValidar);

		return m.find() ? true : false;
	}
}
