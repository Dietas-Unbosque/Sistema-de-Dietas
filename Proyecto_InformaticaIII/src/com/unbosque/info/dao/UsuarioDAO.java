package com.unbosque.info.dao;

import java.util.List;

import com.unbosque.info.entidad.Usuario;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addUser(Usuario user) {
		getSessionFactory().getCurrentSession().save(user);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List <Usuario> getUsers (){
		List listaUsuarios = getSessionFactory().getCurrentSession().createQuery("from Usuario").list();
		return listaUsuarios;
	}
	public Usuario getUserById(int id) {
		@SuppressWarnings("rawtypes")
		List list = getSessionFactory().getCurrentSession()
		.createQuery("from Usuario where id=?").setParameter(0, id)
		.list();
		return (Usuario) list.get(0);
	}
	
	public Usuario getUserLogin (String usuario) {
		@SuppressWarnings("rawtypes")
		List list = getSessionFactory().getCurrentSession()
		.createQuery("from Usuario where login=?").setParameter(0, usuario)
		.list();
		return (Usuario) list.get(0);
	}
	
	public void updateUser (Usuario user){
		getSessionFactory().getCurrentSession().update(user);
	}
	
	public void deleteUser (Usuario user){
		getSessionFactory().getCurrentSession().update(user);
	}


}
