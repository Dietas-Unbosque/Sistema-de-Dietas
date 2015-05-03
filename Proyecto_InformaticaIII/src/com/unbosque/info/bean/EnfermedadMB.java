package com.unbosque.info.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.unbosque.info.entidad.Enfermedad;
import com.unbosque.info.service.EnfermedadService;

@ManagedBean(name = "enfermedadMB")
@SessionScoped
public class EnfermedadMB implements Serializable{
	
	private static final long serialVersionUID = -7809396168460749463L;
	private EnfermedadMB registroSeleccionado;
	@ManagedProperty("#{EnfermedadService}")
	EnfermedadService enfermedadService;
	
	List <Enfermedad> enfermedadList;
	
	private Integer id;
	private String descripcion;
	private String estado;
	
	public void addEnfermedad (){
		try{	
			
			@SuppressWarnings("unused")
			RequestContext context = RequestContext.getCurrentInstance();
							
			Enfermedad enfermedad = new Enfermedad ();
			
			enfermedad.setDescripcion(descripcion);
			enfermedad.setEstado("A");
			enfermedad.setId(id);
			
			getEnfermedadService().addEnfermedad(enfermedad);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregada Exitosamente", "Agregada Exitosamente"));
			
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	public List<Enfermedad> getEnfermedades (){
		enfermedadList = new ArrayList<Enfermedad>();
		enfermedadList.addAll(getEnfermedadService().getEnfermedades());
		for (int i = 0; i<enfermedadList.size();i++){
			if(enfermedadList.get(i).getEstado().equals("A"))
				enfermedadList.get(i).setEstado("Activo");
			else
				enfermedadList.get(i).setEstado("Inactivo");
		}
		return enfermedadList;
	}

	public EnfermedadMB getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(EnfermedadMB registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public EnfermedadService getEnfermedadService() {
		return enfermedadService;
	}

	public void setEnfermedadService(EnfermedadService enfermedadService) {
		this.enfermedadService = enfermedadService;
	}

	public List<Enfermedad> getEnfermedadList() {
		return enfermedadList;
	}

	public void setEnfermedadList(List<Enfermedad> enfermedadList) {
		this.enfermedadList = enfermedadList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
