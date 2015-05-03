package com.unbosque.info.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.unbosque.info.entidad.Dieta;
import com.unbosque.info.service.DietaService;

@ManagedBean (name = "dietaMB")
@SessionScoped
public class DietaMB implements Serializable{

	private static final long serialVersionUID = 5768963164651908680L;
	@ManagedProperty ("#{DietaService}")
	DietaService dietaService;
	
	List <Dieta> dietaList;
	
	private Integer id;
	private String desc;
	private String estado;

	public void addDieta (){
		try{
			@SuppressWarnings("unused")
			RequestContext context = RequestContext.getCurrentInstance();

			Dieta dieta = new Dieta ();

			dieta.setId(id);
			dieta.setDescripcion(desc);
			dieta.setEstado("A");

			getDietaService().addDieta(dieta);
		}catch (DataAccessException e){
			e.printStackTrace();
		}
	}
	
	public List<Dieta> getDietas (){
		dietaList = new ArrayList<Dieta>();
		dietaList.addAll(getDietaService().getDietas());
		return dietaList;
	}
	
	public void eliminarDieta (Dieta dieta, String estado){
		dieta.setEstado(estado);
		getDietaService().delDieta(dieta);
	}
	
	public DietaService getDietaService() {
		return dietaService;
	}
	public void setDietaService(DietaService dietaService) {
		this.dietaService = dietaService;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
