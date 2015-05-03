package com.unbosque.info.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.unbosque.info.entidad.Parametro;
import com.unbosque.info.service.ParametroService;

	@ManagedBean(name = "parametroMB")
	@SessionScoped
	public class ParametroMB implements Serializable {
		@ManagedProperty("#{ParametroService}")
		private static final long serialVersionUID = -4419488537807494697L;
		private ParametroService paramService;
		private Parametro paramSelected;
		private String estado;
		private Integer id;
		private String modulo;
		private String parametro;
		private String valor;
		
		
		public void addParametro (){
			try{
			RequestContext context = RequestContext.getCurrentInstance();
			paramSelected = new Parametro();
			paramSelected.setEstado("A");
			paramSelected.setId(id);
			paramSelected.setModulo(modulo);
			paramSelected.setParametro(parametro);
			paramSelected.setValor(valor);
			getParamService().addParametro(paramSelected);
			limpiarCampos();
			}catch (DataAccessException e){
				e.printStackTrace();
			}
		}
		
		public void limpiarCampos (){
			setEstado("");
			setId(0);
			setModulo("");
			setParametro("");
			setValor("");
		}
		
		public ParametroService getParamService() {
			return paramService;
		}

		public void setParamService(ParametroService paramService) {
			this.paramService = paramService;
		}

		public Parametro getParamSelected() {
			return paramSelected;
		}
		public void setParamSelected(Parametro paramSelected) {
			this.paramSelected = paramSelected;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getModulo() {
			return modulo;
		}
		public void setModulo(String modulo) {
			this.modulo = modulo;
		}
		public String getParametro() {
			return parametro;
		}
		public void setParametro(String parametro) {
			this.parametro = parametro;
		}
		public String getValor() {
			return valor;
		}
		public void setValor(String valor) {
			this.valor = valor;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
}
