package com.arquitecturas.sysacad.logic;

public class Hashtag {

    private static String UTN = "UTN";
    private static String SAN_FCO = "SanFco";

    private int anio;
    private Carrera carrera;
    private String universidad;
    private String localidad;

    public Hashtag() {
	this.universidad = UTN;
	this.carrera = null;
	this.anio = 0;
	this.localidad = SAN_FCO;
    }

    public Hashtag(String universidad) {
	this.universidad = universidad;
	this.carrera = null;
	this.anio = 0;
	this.localidad = SAN_FCO;
    }

    public Hashtag(Carrera carrera) {
	this.universidad = carrera.getUniversidad();
	this.carrera = carrera;
	this.anio = 0;
	this.localidad = SAN_FCO;
    }

    public Hashtag(Carrera carrera, int anio) {
	this.universidad = carrera.getUniversidad();
	this.carrera = carrera;
	this.anio = anio;
	this.localidad = SAN_FCO;
    }

    public int getAnio() {
	return anio;
    }

    public Carrera getCarrera() {
	return carrera;
    }
    
    public String Anio() {
	String iniciales = "";
	if (anio > 0) {
	    iniciales = iniciales + anio;
	}
	return iniciales + Carrera();
    }

    public String Carrera() {
	String iniciales = "";
	if (carrera != null) {
	    iniciales = iniciales + carrera.getIdentificador();
	}
	return iniciales + universidad;
    }

    public String Universidad() {
	return universidad + localidad;
    }

    @Override
    public String toString() {
	return Anio();
    }

}
