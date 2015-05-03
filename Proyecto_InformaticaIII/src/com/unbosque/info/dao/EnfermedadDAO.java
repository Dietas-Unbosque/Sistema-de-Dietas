package com.unbosque.info.dao;

import java.util.List;

import com.unbosque.info.entidad.Enfermedad;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnfermedadDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void addEnfermedad(Enfermedad enfermedad) {
		getSessionFactory().getCurrentSession().save(enfermedad);
	}
	@SuppressWarnings("unchecked")
	public List <Enfermedad> getEnfermedades (){
		@SuppressWarnings("rawtypes")
		List listaEnfermedades = getSessionFactory().getCurrentSession().createQuery("from Enfermedad").list();
		return listaEnfermedades;
	}

}
