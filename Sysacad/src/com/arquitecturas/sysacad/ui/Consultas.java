package com.arquitecturas.sysacad.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Nombres;
import com.arquitecturas.sysacad.utils.AdaptadorExpandibles;

public class Consultas extends ActionBarActivity {

	private String[] opciones = { "Solicitar certificados",
			"Inscripción a examen", "Inscripción a cursado" };
	private ListView lista;
	private String[] opcionesExpandible = { "Mesa especial de examen",
			"Cambio de especialidad", "Pase a otra facultad",
			"Titulo profesional" };
	private Alumno alumno;
	private ExpandableListView listaExpandible;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consultas);

		inicializar();

		listaExpandible.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent in = null;
				switch (childPosition) {
				case 0:
					in = new Intent(Consultas.this, TramiteMesaExamen.class);
					startActivity(in);
					break;
				case 1:
					in = new Intent(Consultas.this,
							TramiteCambioEspecialidad.class);
					startActivity(in);
					break;
				case 2:
					in = new Intent(Consultas.this, TramitePaseFacultad.class);
					startActivity(in);
					break;
				case 3:
					in = new Intent(Consultas.this,
							TramiteTituloProfesional.class);
					startActivity(in);
					break;
				default:
					break;
				}
				return false;
			}
		});

		lista.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Intent in = null;
				switch (position) {
				case 0:
					in = new Intent(Consultas.this, SolicitarCertificados.class);
					break;
				case 1:
					in = new Intent(Consultas.this, InscripcionExamen.class);
					break;
				case 2:
					in = new Intent(Consultas.this, InscripcionCursado.class);
					break;
				default:
					break;
				}
				startActivity(in);
			}
		});
	}

	private void inicializar() {

		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor(Nombres.COLOR_ACTIONBAR)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		lista = (ListView) findViewById(R.id.listView_Consultas);
		alumno = Alumno.getInstance();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.layout_listas, opciones);
		lista.setAdapter(adapter);

		listaExpandible = (ExpandableListView) findViewById(R.id.expandable_tramites);

		ArrayList<String> tramites = new ArrayList<String>();
		tramites.add("Tramites");

		ArrayList<String> tramitesDisponibles = new ArrayList<String>();

		for (String item : opcionesExpandible) {
			tramitesDisponibles.add(item);
		}

		HashMap<String, List<String>> datos = new HashMap<String, List<String>>();
		datos.put(tramites.get(0), tramitesDisponibles);

		AdaptadorExpandibles adapterExpandible = new AdaptadorExpandibles(
				Consultas.this, tramites, datos);
		listaExpandible.setAdapter(adapterExpandible);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.consultas, menu);
		return true;
	}

}
