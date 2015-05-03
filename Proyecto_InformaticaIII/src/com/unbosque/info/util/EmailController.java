package com.unbosque.info.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import com.unbosque.info.entidad.Mail;

public class EmailController {
	private Mail mensagem = new Mail();

	public Mail getMensagem() {
		return mensagem;
	}
	public void setMensagem(Mail mensagem) {
		this.mensagem = mensagem;
	}

	public void enviaEmail() {
		try {
			Email.enviaEmail(mensagem);
		} catch (EmailException ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro! Occoreu um erro ao enviar a mensagem.", "Erro"));
			Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void limpaCampos() {
		mensagem = new Mail();
	}
}