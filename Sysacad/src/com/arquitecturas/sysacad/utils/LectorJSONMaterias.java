package com.arquitecturas.sysacad.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.arquitecturas.sysacad.logic.Materia;
import com.arquitecturas.sysacad.logic.Nombres;

public class LectorJSONMaterias {
    
public static ArrayList<Materia> MateriasDelJson(String json) {
	
	ArrayList<Materia> materias = new ArrayList<Materia>();
	
	try {

	    JSONObject jsonObjet = new JSONObject(json);
	    JSONArray comisiones = jsonObjet.getJSONArray("Comisiones");

	    for (int i = 0; i < comisiones.length(); i++) {
		JSONObject comision = comisiones.getJSONObject(i);
		
		String nombre = comision.getString(Nombres.NOMBRE_MATERIA_JSON);
		String parciales = comision.getString(Nombres.PARCIAL_MATERIA_JSON);
		String condicion = comision.getString(Nombres.CONDICION_MATERIA_JSON);
		String aula = comision.getString(Nombres.AULA_MATERIA_JSON);
		String anio = comision.getString(Nombres.ANIO_MATERIA_JSON);
		String horarios = comision.getString(Nombres.HORARIOS_MATERIA_JSON);
		
		materias.add(new Materia(nombre, parciales, condicion, anio, aula, horarios));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    return null;

	}
	return materias;

    }

}
