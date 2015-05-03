package com.unbosque.info.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unbosque.info.entidad.Parametro;

@Repository
public class ParametroDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addParametro (Parametro parametro){
		getSessionFactory().getCurrentSession().save(parametro);
	}
}
