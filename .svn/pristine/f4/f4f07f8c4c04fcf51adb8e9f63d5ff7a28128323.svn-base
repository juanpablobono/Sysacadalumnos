package com.arquitecturas.sysacad.logic;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Alumno {
	
	private static Alumno alumno = null;
	private int legajo = 0;
	private String nombre = null;
	private String apellido = null;
	private String domicilio = null;
	private int telefono = 0;
	private int dni = 0;	
	private Carrera carrera = null;
	private int idCarrera = 0;
	private String email = null;
	private String password = null;
	private String plan = null;
	
	private SharedPreferences sharedPreferences;
	private Editor editor;
	
	public static void setInstance(SharedPreferences sharedOreferences) {
		alumno = new Alumno(sharedOreferences);
	}
	
	public static Alumno getInstance() {
		return alumno;
	}
	
	private Alumno(SharedPreferences sharedPreferences) {
		this.sharedPreferences = sharedPreferences;
		this.editor = this.sharedPreferences.edit();
	}

	public Carrera getCarrera() {
		if(carrera == null) {
			carrera = new Carrera(this.getIdCarrera());
		}
		return carrera;
	}	

	public String getDomicilio() {
		if(domicilio == null) {
			domicilio = sharedPreferences.getString(Nombres.DOMICILIO, "");
		}
		return domicilio;
	}

	public String getNombre() {
		if(nombre == null) {
			nombre = sharedPreferences.getString(Nombres.NOMBRE, "");
		}
		return nombre;
	}
	
	public String getApellido() {
		if(apellido == null) {
			apellido = sharedPreferences.getString(Nombres.APELLIDO, "");
		}
		return apellido;
	}
	
	public int getDni() {
		if(dni == 0) {
			dni = sharedPreferences.getInt(Nombres.DNI, 0);
		}
		return dni;
	}
	
	private int getIdCarrera() {
		if(idCarrera == 0) {
			idCarrera = sharedPreferences.getInt(Nombres.ID_CARRERA, 0);
		}
		return idCarrera;
	}
	
	public int getTelefono() {
		if(telefono == 0) {
			telefono = sharedPreferences.getInt(Nombres.TELEFONO, 0);
		}
		return telefono;
	}
	
	public String getEmail() {
		if(email == null) {
			email = sharedPreferences.getString(Nombres.EMAIL, "");
		}
		return email;
	}

	public int getLegajo() {
		if(legajo == 0) {
			legajo = sharedPreferences.getInt(Nombres.LEGAJO, 0);
		}
		return legajo;
	}

	public String getPassword() {
		return password;
	}
	
	public String getPlan(){
		if (plan == null) {
			plan = sharedPreferences.getString(Nombres.PLAN, "");
		}
		return plan;
	}
	
	public void setLegajo(int legajo) {
		this.legajo = legajo;
		editor.putInt(Nombres.LEGAJO, legajo);
		editor.commit();
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		editor.putString(Nombres.NOMBRE, nombre);
		editor.commit();
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
		editor.putString(Nombres.APELLIDO, apellido);
		editor.commit();
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
		editor.putString(Nombres.DOMICILIO, domicilio);
		editor.commit();
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
		editor.putInt(Nombres.TELEFONO, telefono);
		editor.commit();
	}

	public void setDni(int dni) {
		this.dni = dni;
		editor.putInt(Nombres.DNI, dni);
		editor.commit();
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
		editor.putInt(Nombres.ID_CARRERA, idCarrera);
		editor.commit();
	}
		
	public void setEmail(String email) {
		this.email = email;
		editor.putString(Nombres.EMAIL, email);
		editor.commit();	
	}
	
	public void setPassword(String password) {
		if(password == null) {
			this.password = null;
			return;
		}
		this.password = password;
	}
	
	public void setPlan(String plan){
		this.plan = plan;
		editor.putString(Nombres.PLAN, plan);
		editor.commit();	
	}
	

}
