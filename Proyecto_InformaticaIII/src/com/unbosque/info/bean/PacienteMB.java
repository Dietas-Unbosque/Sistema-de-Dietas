package com.unbosque.info.bean;

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
import com.unbosque.info.entidad.Paciente;
import com.unbosque.info.service.PacienteService;
import com.unbosque.info.util.Email;
import com.unbosque.info.util.ValidarEmail;

@ManagedBean(name = "pacienteMB")
@SessionScoped

public class PacienteMB implements Serializable{

	private static final long serialVersionUID = -839758297635437781L;

	private PacienteMB registroSeleccionado;

	// Spring Customer Service is injected...
	@ManagedProperty("#{PacienteService}")
	PacienteService pacienteService;

	List <Paciente> pacienteList;

	private Integer id;
	private Integer identificacion;
	private String iden = "";
	private String nombresApellidos;
	private String estado;
	private String telefono;
	private String correo;
	private String progNutricion;

	
	private Paciente pacien;
	
	public void addPaciente () throws EmailException{
		try{	

			@SuppressWarnings("unused")
			RequestContext context = RequestContext.getCurrentInstance();

			Paciente paciente = new Paciente ();
			ValidarEmail validar = new ValidarEmail();
			if(validar.validate(correo)){
			paciente.setId(id);
			paciente.setIdentificacion(Integer.parseInt(iden));
			paciente.setNombresApellidos(nombresApellidos);
			paciente.setEstado("A");
			paciente.setTelefono(telefono);
			paciente.setCorreo(correo);
			paciente.setProgNutricion(progNutricion);

			getPacienteService().addPaciente(paciente);
			enviarMail(iden,nombresApellidos,telefono,correo,progNutricion);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Exitosamente", "Agregado Exitosamente"));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Verifique el Email", "Error Verifique el Email"));
			}

		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}

	public void enviarMail (String iden,String nombres,String telefono,String correo, String pgn) throws EmailException{

		if(pgn.equals("S"))
			pgn = "Afiliado";
		else
			pgn = "No Afiliado";
		Mail mail = new Mail ();
		mail.setDestino(correo);
		String text = "¡Bienvenido Sr.(a): "+nombres+"\nSe han sido registrado los siguientes datos a nombre suyo: "+"\n"
				+"\nNombre: "+nombres+"\nTeléfono: "+telefono+"\nCorreo: "+correo+"\nPrograma de Nutrición: "+pgn+"\n"+
				"\n¡Esperamos que tenga una buena experiencia!";
		mail.setMensagem(text);
		mail.setTitulo("Sistema de Gestión de Dietas UNBOSQUE");
		Email enviar = new Email ();
		enviar.enviaEmail(mail);
	}

	public List<Paciente> getPacientes (){
		pacienteList = new ArrayList<Paciente>();
		pacienteList.addAll(getPacienteService().getPacientes());
		return pacienteList;
	}

	public void eliminar (Paciente pa, String est){
		pa.setEstado(est);
		getPacienteService().eliminarPaciente(pa);
	}

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public PacienteMB getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(PacienteMB registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public List<Paciente> getPacienteList() {
		return pacienteList;
	}

	public void setPacienteList(List<Paciente> pacienteList) {
		this.pacienteList = pacienteList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Integer identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombresApellidos() {
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getProgNutricion() {
		return progNutricion;
	}

	public void setProgNutricion(String progNutricion) {
		this.progNutricion = progNutricion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
