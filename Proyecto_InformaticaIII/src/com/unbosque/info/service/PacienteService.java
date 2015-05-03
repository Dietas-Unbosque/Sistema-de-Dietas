package com.unbosque.info.service;

import java.util.List;

import com.unbosque.info.dao.PacienteDAO;
import com.unbosque.info.entidad.Paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("PacienteService")
@Transactional(readOnly = true)
public class PacienteService {
	
	@Autowired
	PacienteDAO pacienteDAO;
	
	@Transactional(readOnly = false)
	public void addPaciente(Paciente paciente) {
		getPacienteDAO().addPaciente(paciente);
	}
	public List<Paciente> getPacientes (){
		return getPacienteDAO().getPacientes();
	}
	@Transactional(readOnly = false)
	public void eliminarPaciente(Paciente paciente){
		getPacienteDAO().eliminarPaciente(paciente);
	}
	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}
	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

}
