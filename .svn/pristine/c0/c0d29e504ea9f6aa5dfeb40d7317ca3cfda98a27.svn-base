package com.arquitecturas.sysacad.utils;

import com.arquitecturas.sysacad.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends ArrayAdapter<String>{
	
	private final Context context;
	private final String[] values;
	private final String[] descripciones;

	  public Adaptador(Context context, String[] values, String[] descripciones) {
	    super(context, R.layout.listview_main, values);
	    this.context = context;
	    this.values = values;
	    this.descripciones = descripciones;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View vista = inflater.inflate(R.layout.listview_main, parent, false);
	    TextView titulo = (TextView) vista.findViewById(R.id.titulo);
	    TextView descripcion = (TextView) vista.findViewById(R.id.descripcion);
	    
	    titulo.setText(values[position]);
	    descripcion.setText(descripciones[position]);

	    return vista;
	  }
} 

