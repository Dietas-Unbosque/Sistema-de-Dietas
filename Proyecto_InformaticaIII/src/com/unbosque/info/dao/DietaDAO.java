package com.unbosque.info.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unbosque.info.entidad.Dieta;

@Repository
public class DietaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void delDieta (Dieta dieta){
		getSessionFactory().getCurrentSession().update(dieta);
	}
	
	public void adDieta (Dieta dieta){
		getSessionFactory().getCurrentSession().save(dieta);
	}
	@SuppressWarnings("unchecked")
	public List <Dieta> getDietas (){
		@SuppressWarnings("rawtypes")
		List listaDietas = getSessionFactory().getCurrentSession().createQuery("from Dieta").list();
		return listaDietas;
	}
}
