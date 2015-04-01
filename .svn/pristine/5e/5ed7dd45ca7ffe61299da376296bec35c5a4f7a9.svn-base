package com.arquitecturas.sysacad.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Evento;
import com.arquitecturas.sysacad.logic.Nombres;
import com.arquitecturas.sysacad.sysacadservicio.GetSysacadAsyncTask;
import com.arquitecturas.sysacad.ui.SolicitarCertificados.SpinnerListener;
import com.arquitecturas.sysacad.utils.Dialogo;
import com.arquitecturas.sysacad.utils.LectorJSONcalendario;

import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;

public class Calendario extends ActionBarActivity {

	private Button but_sincronizar;
	private Spinner spinner_carreras;
	private String json;
	private ArrayList<String> especialidades;
	private ArrayList<String> tiposEventos;
	private Alumno alumno;
	RelativeLayout layout;
	private Calendario calendarioActivity;
	private ArrayList<CheckBox> checks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendario);
		
		inicializar();
		calendarioActivity = this;
		
		try{
			new GetSysacadAsyncTask() {
				protected void onPostExecute(String result) {
					super.onPostExecute(result);

					try {
						
						json = result;
						especialidades = LectorJSONcalendario.especialidades(json);
						
						ArrayAdapter<String> adapter = new ArrayAdapter<String>(calendarioActivity, 
								R.layout.layout_listas, especialidades);
						spinner_carreras.setAdapter(adapter);
						
						spinner_carreras.setOnItemSelectedListener((OnItemSelectedListener) new SpinnerListener());
/*
						agregarCalendario(LectorJSONcalendario.eventos(result,
									spinner_carreras
											.getSelectedItem()
											.toString(),
									Nombres.EXAMENES_JSON));*/
						
					} catch (Exception e) {
						Log.e("GetCalendarioTask",
							"Error:" + e.getMessage());
					}
				}

			}.execute(GetSysacadAsyncTask.CALENDARIO);
		}catch(Exception e){
			
		}
	
		but_sincronizar.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
			}
			@SuppressLint("NewApi")	
			private void agregarAcalendario(String titulo, String ubicacion, String fecha){
				//for (Evento eventoActual : listaEventos) {
				//	eventoActual.getAnio(); 
				//	eventoActual.getEspecialidad(); 
				//	eventoActual.getTipo(); 
				//}
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		        Date date = null;
		        try {
		            date = formatter.parse(fecha);
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		        long ms = date.getTime();

		        if (Build.VERSION.SDK_INT >= 15) {
		            ContentResolver cr = getContentResolver();
		            ContentValues values = new ContentValues();
		            TimeZone timeZone = TimeZone.getDefault();
		            values.put(CalendarContract.Events.DTSTART, ms);
		            values.put(CalendarContract.Events.DTEND, ms);
		            values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
		            values.put(CalendarContract.Events.TITLE, titulo);
		            values.put(CalendarContract.Events.DESCRIPTION, ubicacion);
		            values.put(CalendarContract.Events.CALENDAR_ID, 1);
		            values.put(CalendarContract.Events.HAS_ALARM, true);
		            values.put(CalendarContract.Events.ALL_DAY, 1);
		            Toast toast = Toast.makeText(getApplicationContext(), "Eventos añadidos correctamente", Toast.LENGTH_SHORT);
		            toast.show();
		            
		        }else{
		            String eventUriString = "content://com.android.calendar/events";
		            ContentValues eventValues = new ContentValues();
		            eventValues.put("calendar_id", 1);
		            eventValues.put("title", titulo);
		            eventValues.put("description", ubicacion);
		            eventValues.put("dtstart", ms);
		            eventValues.put("dtend", ms);
		            eventValues.put("hasAlarm", 1);
		            Toast toast = Toast.makeText(getApplicationContext(), "Eventos añadidos correctamente", Toast.LENGTH_SHORT);
		            toast.show();
		            
		        }
			}
		});

	}
	
	
	public class SpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			if(checks != null){
				for (int i = 0; i < checks.size(); i++) {
					layout.removeView(checks.get(i));
				}
			}
			
			checks = new ArrayList<CheckBox>();
			checks.clear();
			
			tiposEventos = LectorJSONcalendario.tiposDeEventos(json, String.valueOf(spinner_carreras.getSelectedItem()));
			
			android.widget.RelativeLayout.LayoutParams lp;
			spinner_carreras.setId(R.id.spinner_carreras);
			
			View recent = spinner_carreras;
			
			for (int i = 0; i < tiposEventos.size(); i++) {
				
				CheckBox checkTipoEvento = new CheckBox(calendarioActivity);
				checkTipoEvento.setId(i+1);
				
				checks.add(checkTipoEvento);
				
				lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.BELOW, recent.getId());
                
                checkTipoEvento.setText(tiposEventos.get(i));
				layout.addView(checkTipoEvento, lp);
				recent = checkTipoEvento;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// no se hace nada
			
		}
	}

	private void inicializar() {
		layout = (RelativeLayout) findViewById(R.id.layoutControles);
		but_sincronizar = (Button) findViewById(R.id.but_calendario);
		spinner_carreras = (Spinner) findViewById(R.id.spinner_carreras);
		checks = null;
		
		alumno = Alumno.getInstance();

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor(Nombres.COLOR_ACTIONBAR)));
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		if (MainActivity.isUsuarioLogeado()) {
			getSupportActionBar().setSubtitle(
					"Identificado como " + alumno.getNombre() + " "
							+ alumno.getApellido());
		} else {
			getSupportActionBar().setSubtitle("Ninguna sesión iniciada");
		}

	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// NavUtils.navigateUpFromSameTask(this);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.calendario, menu);
		return true;
	}



}
