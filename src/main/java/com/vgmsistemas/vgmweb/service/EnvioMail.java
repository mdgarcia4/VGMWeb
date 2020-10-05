package com.vgmsistemas.vgmweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EnvioMail {
	//Importante hacer la inyección de dependencia de JavaMailSender:
    @Autowired
    private JavaMailSender mailSender;
	static Logger logger = LoggerFactory.getLogger(EnvioMail.class);

    //Pasamos por parametro: destinatario, asunto y el mensaje
    public void sendEmail(String to, String subject, String content) {
    	try {
            SimpleMailMessage email = new SimpleMailMessage();

            email.setTo(to);
            email.setSubject(subject);
            email.setText(content);
           
            mailSender.send(email);
    	} catch (MailParseException mpe) {
			logger.error("Error inesperado en clase EnvioMail-Met: sendEmail. El mensaje en el email no puede ser parseado.");		
			throw mpe;
		} catch (MailAuthenticationException mae) {
			logger.error("Error inesperado en clase EnvioMail-Met: sendEmail. No es posible autenticarse con el email.");	
			throw mae;
		} catch (MailSendException mse) {
			logger.error("Error inesperado en clase EnvioMail-Met: sendEmail. Problemas en el envío del email.");	
			throw mse;
		} catch (MailException me) {
			logger.error("Error inesperado en clase EnvioMail-Met: sendEmail. Problemas general en el envío del email.");	
			throw me;
		} catch (UsernameNotFoundException ex) {
			logger.error("Error inesperado en clase EnvioMail-Met: sendEmail. El email configurado no es correcto o la clave no es valida.");	
			throw ex;
		} catch (Exception e) {
			logger.error("Error inesperado en clase EnvioMail-Met: sendEmail. Exception general");	
			throw e;
		}
    }
}
