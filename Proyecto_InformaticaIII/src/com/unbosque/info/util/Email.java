package com.unbosque.info.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.unbosque.info.entidad.Mail;

public class Email {
	private static final String HOSTNAME = "smtp.gmail.com";
	private static final String USERNAME = "unbosque.dietas";
	private static final String PASSWORD = "dietas123";
	private static final String EMAILORIGEM = "unbosque.dietas@gmail.com";

	public static SimpleEmail conectaEmail() throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setTLS(true);
		email.setFrom(EMAILORIGEM);
		return email;
	}

	public static void enviaEmail(Mail configuracionEnviar) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email = conectaEmail();
		email.setSubject(configuracionEnviar.getTitulo());
		email.setMsg(configuracionEnviar.getMensagem());
		email.addTo(configuracionEnviar.getDestino());
		String resposta = email.send();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado con exito para: " + configuracionEnviar.getDestino(), "Informação"));
	}
}