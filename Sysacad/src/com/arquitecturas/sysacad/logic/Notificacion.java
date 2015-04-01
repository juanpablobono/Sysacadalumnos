package com.arquitecturas.sysacad.logic;

import java.util.Date;

public class Notificacion {

	// ATRIBUTOS
	private String texto;
	private Date fechaYHora;
	private String fechaYHoraString;
	private String idTweet;

	

	// GETTERS Y SETTERS
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(Date fechaYHora2) {
		this.fechaYHora = fechaYHora2;
	}

	public String getFechaYHoraString() {
		return fechaYHoraString;
	}

	public void setFechaYHoraString(String fechaYHoraString) {
		this.fechaYHoraString = fechaYHoraString;
	}
	
	public String getIdTweet() {
		return idTweet;
	}

	public void setIdTweet(String idTweet) {
		this.idTweet = idTweet;
	}

}
