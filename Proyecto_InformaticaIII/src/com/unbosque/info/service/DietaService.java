package com.unbosque.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unbosque.info.dao.DietaDAO;
import com.unbosque.info.entidad.Dieta;

@Service ("DietaService")
@Transactional(readOnly = true)
public class DietaService {
	
	@Autowired
	DietaDAO dietaDAO;
	
	@Transactional(readOnly = false)
	public void addDieta (Dieta dieta){
		getDietaDAO().adDieta(dieta);
	}
	
	@Transactional(readOnly = false)
	public void delDieta (Dieta dieta){
		getDietaDAO().delDieta(dieta);
	}
	
	public List<Dieta> getDietas (){
		return getDietaDAO().getDietas();
	}
	public DietaDAO getDietaDAO() {
		return dietaDAO;
	}

	public void setDietaDAO(DietaDAO dietaDAO) {
		this.dietaDAO = dietaDAO;
	}
}
