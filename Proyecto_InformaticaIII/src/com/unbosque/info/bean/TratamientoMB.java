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

import com.unbosque.info.entidad.Tratamiento;
import com.unbosque.info.service.TratamientoService;

@ManagedBean(name = "tratamientoMB")
@SessionScoped

public class TratamientoMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private TratamientoMB registroSeleccionado;

	@ManagedProperty("#{TratamientoService}")
	TratamientoService tratamientoService;
	
	List <Tratamiento> tratamientoList;
	
	private Integer id;
	private String descripcion;
	private String estado;
	
	public void addTratamiento (){
		try{	
			
			@SuppressWarnings("unused")
			RequestContext context = RequestContext.getCurrentInstance();
							
			Tratamiento tratamiento = new Tratamiento ();
			
			tratamiento.setDescripcion(descripcion);
			tratamiento.setEstado("A");
			tratamiento.setId(id);
			
			getTratamientoService().addTratamiento(tratamiento);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregada Exitosamente", "Agregada Exitosamente"));
			
		}catch(DataAccessException e){
			e.printStackTrace();
		}
	}
	
	public List<Tratamiento> getTratamientos (){
		tratamientoList = new ArrayList<Tratamiento>();
		tratamientoList.addAll(getTratamientoService().getTratamientos());
		
		return tratamientoList;
	}
	
	public void eliminarTratamiento (Tratamiento tr, String est){
		tr.setEstado(est);
		getTratamientoService().delTratamiento(tr);
	}
	
	public TratamientoMB getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public TratamientoService getTratamientoService() {
		return tratamientoService;
	}

	public void setTratamientoService(TratamientoService tratamientoService) {
		this.tratamientoService = tratamientoService;
	}

	public List<Tratamiento> getTratamientoList() {
		return tratamientoList;
	}

	public void setTratamientoList(List<Tratamiento> tratamientoList) {
		this.tratamientoList = tratamientoList;
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

	public void setRegistroSeleccionado(TratamientoMB registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}
	
}
