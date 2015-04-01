package com.arquitecturas.sysacad.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.audiofx.Equalizer;

import com.arquitecturas.sysacad.logic.Evento;
import com.arquitecturas.sysacad.logic.Nombres;

/*
 * Para parsear el Json del calendario, la clase LectorJSON no se usa mas, se usa esta.
 * Al metodo leer calendario se le debe pasar el json, la especialidad (ingenieria o tec en programacio, por ejemplo)
 * y el tipo de evento a buscar (Examenes, Feriados, Cuatrimestres, etc). El metodo devolverá un array de Eventos.
 * Cada Evento a su vez tendra un vecor Date[] de fechas. Por el momento, segun los datos que vienen en el json, cada evento
 * puede tener o bien una unica fecha, o bien dos fechas en donde la primera, Date[0], es la fecha de inicio del evento, y la
 * segunda, Date[1], es la de fin del evento.
 * 
 * Cualquier fecha puede ser null, generalmente la fecha de fin de un evento que tenga fechas de inicio y fin. Por lo tanto
 * se debe verificar que una fecha no sea nula antes de consultarla.
 * 
 * */

public class LectorJSONcalendario {
	
	public static ArrayList<String> especialidades(String documentoJson){
		
		ArrayList<String> especialidades = new ArrayList<String>();
		
		JSONObject objetoJson;
		
		try{
			objetoJson = new JSONObject(documentoJson); //Objeto general
			
			if (objetoJson.getString(Nombres.ESTADO).equalsIgnoreCase("2 - OK")) {
				
				JSONObject objetoCalendarios = objetoJson.getJSONObject(Nombres.CALENDARIOS_JSON).getJSONObject(Nombres.CALENDARIOS_JSON);
				JSONArray arrayCalendario = objetoCalendarios.getJSONArray(Nombres.CALENDARIO_JSON);
				
				//System.out.println("***********Especialidades*************************");
				
				for (int i = 0; i < arrayCalendario.length(); i++) {
					try {
						
						JSONObject unCalendario = arrayCalendario.getJSONObject(i);
						especialidades.add(unCalendario.getString(Nombres.ESPECIALIDAD));
						//System.out.println(unCalendario.getString(Nombres.ESPECIALIDAD));
						
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				//System.out.println("***********Especialidades*************************");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return especialidades;
	}
	
	public static ArrayList<String> tiposDeEventos(String documentoJson, String especialidad){
		
		ArrayList<String> tiposDeEventos = new ArrayList<String>();
		
		JSONObject objetoJson;
		
		try{
			objetoJson = new JSONObject(documentoJson); //Objeto general
			
			if (objetoJson.getString(Nombres.ESTADO).equalsIgnoreCase("2 - OK")) {
				
				JSONObject objetoCalendarios = objetoJson.getJSONObject(Nombres.CALENDARIOS_JSON).getJSONObject(Nombres.CALENDARIOS_JSON);
				JSONArray arrayCalendario = objetoCalendarios.getJSONArray(Nombres.CALENDARIO_JSON);
				
				for (int i = 0; i < arrayCalendario.length(); i++) {
					try {
						
						JSONObject unCalendario = arrayCalendario.getJSONObject(i);
						if(unCalendario.getString(Nombres.ESPECIALIDAD).equalsIgnoreCase(especialidad)){
							
							Iterator iterador = unCalendario.keys();
							
							JSONObject objectEvento = new JSONObject();
							
							while(iterador.hasNext()){								
								//objectEvento = (JSONObject) iterador.next();
								tiposDeEventos.add(iterador.next().toString());							
							}
							
							for (int j = 0; j < tiposDeEventos.size(); j++) {
								
								if(tiposDeEventos.get(j).equalsIgnoreCase("Año") ||
										tiposDeEventos.get(j).equalsIgnoreCase("Especialidad")){
									tiposDeEventos.remove(j);
								}
							}
							
//							System.out.println("****************Tipos de eventos**************************");
//							for (int j = 0; j < tiposDeEventos.size(); j++) {
//								System.out.println("\n" + tiposDeEventos.get(j));
//							}
//							System.out.println("****************Tipos de eventos**************************");
							
						}
						
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return tiposDeEventos;
	}
	
	public static ArrayList<Evento> eventos(String documentoJson, String especialidad, String tipoEvento) {
		
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		int anio;
		
		JSONObject objetoJson;
		
		try{
			objetoJson = new JSONObject(documentoJson); //Objeto general
			
			if (objetoJson.getString(Nombres.ESTADO).equalsIgnoreCase("2 - OK")) {
				
				JSONObject objetoCalendarios = objetoJson.getJSONObject(Nombres.CALENDARIOS_JSON).getJSONObject(Nombres.CALENDARIOS_JSON);
				JSONArray arrayCalendario = objetoCalendarios.getJSONArray(Nombres.CALENDARIO_JSON);
				
				for (int i = 0; i < arrayCalendario.length(); i++) {
					try {
						
						JSONObject unCalendario = arrayCalendario.getJSONObject(i);
						anio = Integer.parseInt(unCalendario.getString(Nombres.ANIO));
						
						if(unCalendario.getString(Nombres.ESPECIALIDAD).equalsIgnoreCase(especialidad)) {
							
							JSONObject objetoEvento = unCalendario.getJSONObject(tipoEvento);
							String tipoEventoArray = TipoEventoSingular(tipoEvento);
							JSONArray arrayEvento = objetoEvento.getJSONArray(tipoEventoArray);
							
							for (int j = 0; j < arrayEvento.length(); j++) {
								
								if (tipoEvento == Nombres.PRIMEROS_ANIOS || tipoEvento == Nombres.RECESOS 
										|| tipoEvento == Nombres.CUATRIMESTRES || tipoEvento == "Cursillos") {
									
									JSONObject itemEvento = arrayEvento.getJSONObject(j);
									
									Date fechaInicio = null;
									if(itemEvento.getString(Nombres.INICIO) != "null"){
										fechaInicio = FormateadoFechas.parsear(itemEvento.getString(Nombres.INICIO));
									}
									Date fechaFin = null;
									if(itemEvento.getString(Nombres.FIN) != "null"){
										fechaFin = FormateadoFechas.parsear(itemEvento.getString(Nombres.FIN));
									}
									
									eventos.add(new Evento(anio, especialidad, tipoEvento, null, fechaInicio, fechaFin));
									
								} else {
									
									JSONObject itemEvento = arrayEvento.getJSONObject(j);
									
									Date fecha = null;
									if(itemEvento.getString(Nombres.FECHA) != "null"){
										fecha = FormateadoFechas.parsear(itemEvento.getString(Nombres.FECHA));
									}
									
									Evento nuevoEvento = new Evento(anio, especialidad, tipoEvento, fecha, null, null);
									eventos.add(nuevoEvento);
								}
							}
						}
						
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return eventos;
	}

	private static String TipoEventoSingular(String tipoEvento){
			String tipoEventoSingular = "";
		
			if(tipoEvento == "Examenes"){ 
				tipoEventoSingular = "Examen";
			}
		
			if(tipoEvento == "Cuatrimestres"){ 
				tipoEventoSingular = "Cuatrimestre";
			}
		
			if(tipoEvento == "Cursillos"){ 
				tipoEventoSingular = "Cursillo";
			}
		
			if(tipoEvento == "PrimerosAños"){ 
				tipoEventoSingular = "PrimerAño";
			}
		
			if(tipoEvento == "Feriados"){ 
				tipoEventoSingular = "Feriado";
			}
		
			if(tipoEvento == "Recesos"){ 
				tipoEventoSingular = "Receso";
			}
		
			return tipoEventoSingular;
		}

}
