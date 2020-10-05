package com.vgmsistemas.vgmweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.dto.ContactoConsulta;

@Service
public class ContactoService {
	@Autowired
	EnvioMail mail;
	@Autowired
	PropertiesService propertyService;
	static Logger logger = LoggerFactory.getLogger(ContactoService.class);

	public String mandarConsulta(ContactoConsulta consulta) {
		String respuesta,msjMail = "";
		try {
			msjMail = "Consulta de " + consulta.getName() + ";\n";
			msjMail += "Correo electrónico: " + consulta.getEmail() + ".\n";
			msjMail += "Título de la consulta: " + consulta.getSub() + ".\n";
			msjMail += "Comentario: " + consulta.getMessage() + ".\n";
			mail.sendEmail(propertyService.getEmailDeConsulta(), propertyService.getAsuntoContulta(), msjMail);
			respuesta = "OK";
		} catch (MailParseException mpe) {
			logger.error("Error inesperado en clase ContactoService-Met: mandarConsulta. El mensaje en el email no puede ser parseado.");		
			throw mpe;
		} catch (MailAuthenticationException mae) {
			logger.error("Error inesperado en clase ContactoService-Met: mandarConsulta. No es posible autenticarse con el email.");	
			throw mae;
		} catch (MailSendException mse) {
			logger.error("Error inesperado en clase ContactoService-Met: mandarConsulta. Problemas en el envío del email.");	
			throw mse;
		} catch (MailException me) {
			logger.error("Error inesperado en clase ContactoService-Met: mandarConsulta. Problemas general en el envío del email.");	
			throw me;
		} catch (UsernameNotFoundException ex) {
			logger.error("Error inesperado en clase ContactoService-Met: mandarConsulta. El email configurado no es correcto o la clave no es valida.");	
			throw ex;
		} catch (Exception e) {
			logger.error("Error inesperado en clase ContactoService-Met: mandarConsulta. Exception general");	
			throw e;
		}
		return respuesta;
	}

}
