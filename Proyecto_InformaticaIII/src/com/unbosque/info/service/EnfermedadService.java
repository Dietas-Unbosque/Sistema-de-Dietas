package com.unbosque.info.service;

import java.util.List;

import com.unbosque.info.dao.EnfermedadDAO;
import com.unbosque.info.entidad.Enfermedad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("EnfermedadService")
@Transactional(readOnly = true)
public class EnfermedadService {
	
	@Autowired
	EnfermedadDAO enfermedadDAO;
	
	@Transactional(readOnly = false)
	public void addEnfermedad(Enfermedad enfermedad) {
		getEnfermedadDAO().addEnfermedad(enfermedad);
	}
	public List<Enfermedad> getEnfermedades (){
		return getEnfermedadDAO().getEnfermedades();
	}
	public EnfermedadDAO getEnfermedadDAO() {
		return enfermedadDAO;
	}
	public void setEnfermedadDAO(EnfermedadDAO enfermedadDAO) {
		this.enfermedadDAO = enfermedadDAO;
	}

}
