package com.arquitecturas.sysacad.utils;

import org.json.JSONObject;

import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Carrera;
import com.arquitecturas.sysacad.logic.Nombres;

public class LectorJSONlogin {
	
	private static String nombre;
	private static String apellido;
	private static int dniAlumno;
	private static String plan;
	private static String str_carrera;
	private static Carrera carrera;
	private static int legajo;
	
	public static String leerEstadoLogin(String documentoJson) {
		
		JSONObject json;
		
		String estado = null;
		
		try {
			json =  new JSONObject(documentoJson);
			
			estado = json.getString(Nombres.ESTADO);
			
			String nombreCompleto = json.getString("Nombre");
			separarNombreYApellido(nombreCompleto);
			
			dniAlumno = Integer.parseInt(json.getString("DniAlumno"));
			plan = json.getString("Plan");
			str_carrera = json.getString("Especialidad");
			carrera = Carrera.CarreraConNombre(str_carrera);
			legajo = Integer.parseInt(json.getString("Legajo"));
			
			Alumno alumno = Alumno.getInstance();
			
			alumno.setLegajo(legajo);
			alumno.setNombre(nombre);
			alumno.setApellido(apellido);
			alumno.setDni(dniAlumno);
			alumno.setPlan(plan);
			
			int idCarrera = carrera.getId();
			alumno.setIdCarrera(idCarrera);
			
//			System.out.println("*********************************************************");
//			System.out.println(String.valueOf(alumno.getLegajo()) + " - " + alumno.getNombre() 
//					+ " " + alumno.getApellido() + " - " + String.valueOf(alumno.getDni()) + " - " 
//					+ alumno.getPlan() + " - " + alumno.getCarrera().getNombre());
//			System.out.println("*********************************************************");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return estado;
		}
	}
	
	
	private static void separarNombreYApellido(String nombreCompleto){
		
		int comienzoApellido = 0;
		int finApellido = -1;
		int comienzoNombre = -1;
		int finNombre = -1;
		
		for (int i = 0; i < nombreCompleto.length(); i++) {
			if(nombreCompleto.charAt(i) == ','){
				finApellido = i;
				comienzoNombre = i + 2;
				
				int k = comienzoNombre;
				while (k < nombreCompleto.length()) {
					k++;

					if (nombreCompleto.charAt(k) == ' ') {
						finNombre = k;
						break;
					}
				}
			}
		}
		
		if(finApellido != -1 && comienzoNombre != -1){
			nombre = nombreCompleto.substring(comienzoNombre, finNombre);
			apellido = nombreCompleto.substring(comienzoApellido, finApellido);
			
			//System.out.println(nombre + "_" + apellido);
		}
	}
}
