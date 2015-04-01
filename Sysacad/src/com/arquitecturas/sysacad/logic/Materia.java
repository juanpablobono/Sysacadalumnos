package com.arquitecturas.sysacad.logic;

public class Materia {
    
    private String Nombre;
    private String Parciales;
    private String Condicion;
    private String anio;
    private String aula;
    private String horarios;

    public Materia(String nombre, String parciales, String condicion,
			String anio, String aula, String horarios) {
		super();
		Nombre = nombre;
		Parciales = parciales;
		Condicion = condicion;
		this.anio = anio;
		this.aula = aula;
		this.horarios = horarios;
	}

	public String getNombre() {
        return Nombre;
    }

    public String getParciales() {
        return Parciales;
    }

    public String getCondicion() {
        return Condicion;
    }

    @Override
    public String toString() {
	return Nombre;
    }

	public String getAnio() {
		return anio;
	}

	public String getAula() {
		return aula;
	}

	public String getHorarios() {
		return horarios;
	}
}
