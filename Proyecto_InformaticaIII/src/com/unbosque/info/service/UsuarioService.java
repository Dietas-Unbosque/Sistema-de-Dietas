package com.unbosque.info.service;

import java.util.List;

import com.unbosque.info.dao.UsuarioDAO;
import com.unbosque.info.entidad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UsuarioService")
@Transactional(readOnly = true)
public class UsuarioService{

	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Transactional(readOnly = false)
	public void addUsuario(Usuario user) {
		getUsuarioDAO().addUser(user);
	}
	public Usuario getUser(int id) {
		return getUsuarioDAO().getUserById(id);
	}
	public Usuario getUserLogin(String usuario){
		return getUsuarioDAO().getUserLogin(usuario);
	}
	
	@Transactional(readOnly = false)
	public void updateUser (Usuario user){
		getUsuarioDAO().updateUser(user);
	}
	
	@Transactional(readOnly = false)
	public void deleteUser (Usuario user){
		getUsuarioDAO().deleteUser(user);
	}
	public List<Usuario> getUsers (){
		return getUsuarioDAO().getUsers();
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}
