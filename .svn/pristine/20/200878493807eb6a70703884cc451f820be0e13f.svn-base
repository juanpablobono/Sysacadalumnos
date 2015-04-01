package com.arquitecturas.sysacad.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormateadoFechas {
	private static Date fecha;
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Date parsear(String fechaString) {
		try {
			fecha = formatter.parse(fechaString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fecha;
	}
	
	public static String formatear(Date fechaDate) {
		return formatter.format(fechaDate);
	}
}
