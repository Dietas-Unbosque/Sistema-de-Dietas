package com.unbosque.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unbosque.info.dao.ParametroDAO;
import com.unbosque.info.entidad.Parametro;
import com.unbosque.info.entidad.Usuario;

@Service("ParametroService")
@Transactional(readOnly = true)
public class ParametroService {
	@Autowired
	ParametroDAO parametroDAO;
	
	public void addParametro (Parametro parametro){
		getParametroDAO().addParametro(parametro);
	}
	
	
	public ParametroDAO getParametroDAO() {
		return parametroDAO;
	}

	public void setParametroDAO(ParametroDAO parametroDAO) {
		this.parametroDAO = parametroDAO;
	}
	
	@Transactional(readOnly = false)
	public void deletePar (Parametro par){
		getParametroDAO().deletePar(par);
	}
	
}
