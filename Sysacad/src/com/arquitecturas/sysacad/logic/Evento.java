package com.arquitecturas.sysacad.logic;

import java.util.Date;

public class Evento {
	private int anio;
	private String especialidad;
	private String tipo;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaUnica;
	
	public Evento(int anio, String especialidad, String tipo, Date fechaUnica, Date fechaInicio, Date fechaFin) {
		this.anio = anio;
		this.especialidad = especialidad;
		this.tipo = tipo;
		this.fechaUnica = fechaUnica;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		
		if(fechaUnica != null){
			this.fechaInicio = null;
			this.fechaFin = null;
		}
		if(fechaInicio != null && fechaFin != null){
			this.fechaUnica = null;
		}
		
		
	}

	public int getAnio() {
		return anio;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public String getTipo() {
		return tipo;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public Date getFechaUnica() {
		return fechaUnica;
	}
}
