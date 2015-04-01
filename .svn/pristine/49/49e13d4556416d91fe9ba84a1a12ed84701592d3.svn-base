package com.arquitecturas.sysacad.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.arquitecturas.sysacad.ui.DatosPersonales;

public class Dialogo {
	
	public static void mostrarMensaje(String titulo, String mensaje, Context contexto) {
		AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
		builder.setTitle(titulo);
		builder.setMessage(mensaje);
		
		builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		AlertDialog dialogo = builder.create();
		dialogo.show();		
	}
	
	public static void mostrarMensaje(int titulo, String mensaje, Context contexto) {
		
			AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
			builder.setTitle(titulo);
			builder.setMessage(mensaje);
			
			builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			
			AlertDialog dialogo = builder.create();
			dialogo.show();
	}
	
}
