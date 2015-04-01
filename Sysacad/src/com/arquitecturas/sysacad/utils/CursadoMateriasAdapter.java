package com.arquitecturas.sysacad.utils;

import java.util.ArrayList;
import java.util.List;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Materia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CursadoMateriasAdapter extends ArrayAdapter<Materia> {
	
	private LayoutInflater mInflater;
	private ArrayList<Materia> mMaterias;

	public CursadoMateriasAdapter(Context context, int resource,
			int textViewResourceId, List<Materia> objects) {
		
		super(context, resource, textViewResourceId, objects);
		
		mMaterias = (ArrayList<Materia>) objects;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		
		if(convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.lst_cursado_item, null);
			holder.lblNombre = (TextView) convertView.findViewById(R.id.lblNombreMateria);
			holder.lblCondicion = (TextView) convertView.findViewById(R.id.lblCondicion);
			holder.lblAnio = (TextView) convertView.findViewById(R.id.lblNivel);
			holder.lblAula = (TextView) convertView.findViewById(R.id.lblAula);
			holder.lblHorarios = (TextView) convertView.findViewById(R.id.lblHorarios);
			holder.lblParciales = (TextView) convertView.findViewById(R.id.lblParciales);
			holder.lblForHorarios = (TextView) convertView.findViewById(R.id.lblForHorarios);
			holder.lblForParciales = (TextView) convertView.findViewById(R.id.lblForParciales);
			convertView.setTag(holder);			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		String condicion = mMaterias.get(position).getCondicion();
		String horarios = mMaterias.get(position).getHorarios();
		String parciales = mMaterias.get(position).getParciales();
		
		holder.lblNombre.setText(mMaterias.get(position).getNombre());
		
		if(condicion.equals("")){
			holder.lblCondicion.setVisibility(View.GONE);
		}else{
			holder.lblCondicion.setVisibility(View.VISIBLE);
			holder.lblCondicion.setText(condicion);	
		}
		
		holder.lblAnio.setText(mMaterias.get(position).getAnio() + " nivel. ");
		holder.lblAula.setText(mMaterias.get(position).getAula());
		
		if(horarios.equals("")){
			holder.lblHorarios.setVisibility(View.GONE);
			holder.lblForHorarios.setVisibility(View.GONE);
		}else{
			holder.lblForHorarios.setVisibility(View.VISIBLE);
			holder.lblHorarios.setVisibility(View.VISIBLE);
			holder.lblHorarios.setText(horarios);	
		}
		
		if(parciales.equals("")){
			holder.lblParciales.setVisibility(View.GONE);
			holder.lblForParciales.setVisibility(View.GONE);
		}else{
			holder.lblForParciales.setVisibility(View.VISIBLE);
			holder.lblParciales.setVisibility(View.VISIBLE);
			holder.lblParciales.setText(parciales);	
		}	
		
		return convertView;
	}
	
	private class ViewHolder{
		public TextView lblNombre;
		public TextView lblCondicion;
		public TextView lblAnio;
		public TextView lblAula;
		public TextView lblForHorarios;
		public TextView lblForParciales;
		public TextView lblHorarios;
		public TextView lblParciales;
	}
}
