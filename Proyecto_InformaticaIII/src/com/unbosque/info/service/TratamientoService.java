package com.unbosque.info.service;

import java.util.List;

import com.unbosque.info.dao.TratamientoDAO;
import com.unbosque.info.entidad.Tratamiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TratamientoService")
@Transactional(readOnly = true)
public class TratamientoService {
	
	@Autowired
	TratamientoDAO TratamientoDAO;
	
	@Transactional(readOnly = false)
	public void addTratamiento(Tratamiento tratamiento) {
		getTratamientoDAO().addTratamiento(tratamiento);
	}
	
	@Transactional(readOnly = false)
	public void delTratamiento(Tratamiento tratamiento) {
		getTratamientoDAO().deleteTratamiento(tratamiento);
	}
	
	public List<Tratamiento> getTratamientos (){
		return getTratamientoDAO().getTratamientos();
	}
	public TratamientoDAO getTratamientoDAO() {
		return TratamientoDAO;
	}
	public void setTratamientoDAO(TratamientoDAO tratamientoDAO) {
		TratamientoDAO = tratamientoDAO;
	}
	
}
