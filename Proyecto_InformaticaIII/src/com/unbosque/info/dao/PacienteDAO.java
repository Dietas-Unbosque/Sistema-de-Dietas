package com.unbosque.info.dao;

import java.util.List;

import com.unbosque.info.entidad.Paciente;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PacienteDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addPaciente(Paciente paciente) {
		getSessionFactory().getCurrentSession().save(paciente);
	}
	
	public void eliminarPaciente (Paciente paciente){
		getSessionFactory().getCurrentSession().update(paciente);
	}
	
	@SuppressWarnings("unchecked")
	public List <Paciente> getPacientes (){
		@SuppressWarnings("rawtypes")
		List listaPacientes = getSessionFactory().getCurrentSession().createQuery("from Paciente").list();
		return listaPacientes;
	}

}
