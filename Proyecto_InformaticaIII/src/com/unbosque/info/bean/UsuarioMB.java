package com.unbosque.info.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.unbosque.info.entidad.Mail;
import com.unbosque.info.entidad.Usuario;
import com.unbosque.info.service.UsuarioService;
import com.unbosque.info.util.CifrarClave;
import com.unbosque.info.util.Email;
import com.unbosque.info.util.ValidarEmail;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = -7809396168460749463L;
	private UsuarioMB registroSeleccionado;

	@ManagedProperty("#{UsuarioService}")
	UsuarioService usuarioService;

	List<Usuario> userList;
	List<Usuario> userList2;

	private Integer id;
	private String login;
	private String pass;
	private String apellidosNombres;
	private java.sql.Timestamp fechaClave;
	private java.sql.Timestamp fechaCreacion;
	private String estado;
	private String correo;
	private String tp;

	private Usuario user;

	private String del;

	private String usr;
	private String cla;



	public void addUser() throws EmailException {
		try {

			@SuppressWarnings("unused")
			RequestContext context = RequestContext.getCurrentInstance();

			java.util.Date today = new java.util.Date();
			fechaCreacion = new java.sql.Timestamp(today.getTime());
			fechaClave = new java.sql.Timestamp(today.getTime());

			ValidarEmail validaEmail = new ValidarEmail ();
			if(validaEmail.validate(correo)){
				user = new Usuario();

				user.setApellidosNombres(apellidosNombres);
				user.setId(id);
				CifrarClave cifrado = new CifrarClave();
				String claS = pass;
				pass = cifrado.cifradoClave(pass);
				user.setPassword(pass);
				user.setLogin(login);
				user.setFechaClave(fechaClave);
				user.setFechaCreacion(fechaCreacion);
				user.setCorreo(correo);
				user.setTipoUsuario(tp);
				user.setEstado("A");
				enviarMail(login, claS, apellidosNombres, fechaCreacion,correo,tp);
				getUsuarioService().addUsuario(user);
				resetValues();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Agregado Exitosamente", "Agregado Exitosamente"));
			}else{
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"Email Invalido", "Email Invalido"));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Ya existe un usuario con ese login", "Ya existe un usuario con ese login"));
		}
	}

	public void resetValues() {
		this.setApellidosNombres("");
		this.setId(0);
		this.setPass("");
		this.setLogin("");
		this.setCorreo("");
		this.setTp("");
	}

	public void modifUsuario (){
		RequestContext context = RequestContext.getCurrentInstance();
		if(apellidosNombres.equals("")){
			user.setApellidosNombres(user.getApellidosNombres());
		}else{
			user.setApellidosNombres(apellidosNombres);
		}
		if(correo.equals("")){
			user.setCorreo(user.getCorreo());
		}else{
			user.setCorreo(correo);
		}
		CifrarClave clave = new CifrarClave();
		pass = clave.cifradoClave(pass);
		if(pass.equals("")){
			user.setPassword(user.getPassword());
		}else{
			user.setPassword(pass);
		}
		resetValues();
		getUsuarioService().updateUser(user);
	}

	public Usuario deleteUser() {
		Usuario user;
		String log, nom, est;
		user = getUsuarioService().getUser(Integer.parseInt(del));
		log = "Usuario: " + user.getLogin();
		nom = "Nombre: " + user.getApellidosNombres();
		if (user.getEstado().equals("A"))
			est = "Estado: Activo";
		else
			est = "Estado: Inactivo";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, nom, nom));
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, log, log));
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, est, est));
		return user;
	}

	public void eliminar(Usuario user,String estado) {
		user.setEstado(estado);
		getUsuarioService().deleteUser(user);
	}

	public void validarLogin() throws IOException {
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			Usuario log = getUsuarioService().getUserLogin(usr);

			if(log.getEstado().equals("A")){
				CifrarClave clave = new CifrarClave();
				cla = clave.cifradoClave(cla);
				if(log.getPassword().equals(cla)){
					if(log.getTipoUsuario().equals("A")){
						FacesContext.getCurrentInstance().getExternalContext()
						.redirect("AgregarUsuario.xhtml");
						setUsr("");
						setCla("");
					}else if(log.getTipoUsuario().equals("D")){
						FacesContext.getCurrentInstance().getExternalContext()
						.redirect("AgregarPaciente.xhtml");
						setUsr("");
						setCla("");
					}
				}else{
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Clave Erronea","Clave Erronea" ));
					setCla("");
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario Inactivo","Usuario Inactivo"));
				setUsr("");
				setCla("");
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "No existe el Usuario","No existe el Usuario"));
			setUsr("");
			setCla("");
		}
	}

	public void enviarMail (String usuario, String clave, String nombres, java.util.Date fechaCreacion, String correo, String tipo) throws EmailException{
		if(tipo.equals("D")){
			tipo = "Doctor";
		}else if(tipo.equals("A")){
			tipo = "Administrador de Sistema";
		}
		Mail mail = new Mail ();
		mail.setDestino(correo);
		String text = "Hola "+nombres+"\n"+"\n"+"\nQue gusto tenerlo en el Sistema de Gestión de Dietas, usted ha sido registrado como: "+tipo+"\n"+"\n"
				+"Usuario: "+usuario+"\nCon clave: "+clave+"\nEn la fecha y hora: "+fechaCreacion+"\n"+"\nEsperamos que disfrute la plataforma!!";
		mail.setMensagem(text);
		mail.setTitulo("Bienvenido al Sistema de Gestión de Dietas UNBOSQUE");
		Email enviar = new Email ();
		enviar.enviaEmail(mail);
	}

	public List<Usuario> getUsers() {
		userList2 = new ArrayList<Usuario>();
		userList2.addAll(getUsuarioService().getUsers());
		return userList2;
	}



	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getCla() {
		return cla;
	}

	public void setCla(String cla) {
		this.cla = cla;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public Usuario getUserById(int id) {
		return getUsuarioService().getUser(id);
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public UsuarioMB getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(UsuarioMB registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getApellidosNombres() {
		return apellidosNombres;
	}

	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}

	public java.sql.Timestamp getFechaClave() {
		return fechaClave;
	}

	public void setFechaClave(java.sql.Timestamp fechaClave) {
		this.fechaClave = fechaClave;
	}

	public java.sql.Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public List<Usuario> getUserList() {
		return userList;
	}

	public void setUserList(List<Usuario> userList) {
		this.userList = userList;
	}

	public List<Usuario> getUserList2() {
		return userList2;
	}

	public void setUserList2(List<Usuario> userList2) {
		this.userList2 = userList2;
	}

}
