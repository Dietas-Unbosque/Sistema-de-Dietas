package com.unbosque.info.dao;

import java.util.List;

import com.unbosque.info.entidad.Tratamiento;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TratamientoDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void deleteTratamiento (Tratamiento tr){
		getSessionFactory().getCurrentSession().update(tr);
	}
	
	public void addTratamiento(Tratamiento tratamiento) {
		getSessionFactory().getCurrentSession().save(tratamiento);
	}
	@SuppressWarnings("unchecked")
	public List <Tratamiento> getTratamientos (){
		@SuppressWarnings("rawtypes")
		List listaTratamientos = getSessionFactory().getCurrentSession().createQuery("from Tratamiento").list();
		return listaTratamientos;
	}

}
