package com.arquitecturas.sysacad.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Nombres;
import com.arquitecturas.sysacad.sysacadservicio.GetSysacadAsyncTask;
import com.arquitecturas.sysacad.logic.Materia;
import com.arquitecturas.sysacad.utils.CursadoMateriasAdapter;
import com.arquitecturas.sysacad.utils.LectorJSONMaterias;

public class NotasParciales extends ActionBarActivity {

	String[] opciones = { "Análisis matemático 1",
			"Algoritmos y estructuras de datos", "Física 2",
			"Gestión de datos", "Economía" };
	ListView lista;
	ArrayAdapter<Materia> adapterLista;
	private ArrayList<Materia> Materias;
	private ProgressBar Progress;
	private Alumno alumno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notas_parciales);

		inicializar();

		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						NotasParciales.this);
				String nombreABuscar = adapterLista.getItem(position)
						.toString();

				Materia materia = MateriaConNombre(nombreABuscar);

				builder.setTitle("Notas de " + materia.getNombre());

				String notasParciales = "Parciales: " + materia.getParciales();
				if (materia.getCondicion().length() > 0) {
					notasParciales = notasParciales + "\nCondición: "
							+ materia.getCondicion();
				}

				builder.setMessage(notasParciales);
				builder.setCancelable(false);

				builder.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.show();
			};
		});

	}

	private void inicializar() {
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor(Nombres.COLOR_ACTIONBAR)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		lista = (ListView) findViewById(R.id.listView_NotasParciales);

		Progress = (ProgressBar) findViewById(R.id.progresss);

		alumno = Alumno.getInstance();

		CargarMateriasDelAlumno(alumno);

	}

	private void CargarMateriasDelAlumno(Alumno alumno) {
		new GetSysacadAsyncTask() {

			protected void onPreExecute() {
				Progress.setVisibility(View.VISIBLE);
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);

				Materias = LectorJSONMaterias.MateriasDelJson(result);

				Progress.setVisibility(View.GONE);

				adapterLista = new CursadoMateriasAdapter(
						getApplicationContext(), R.layout.layout_listas, android.R.id.text1,
						Materias);

				lista.setAdapter(adapterLista);
			}

		}.execute(GetSysacadAsyncTask.CURSADO,
				String.valueOf(alumno.getLegajo()), alumno.getPassword());

	}

	private ArrayList<String> NombresDeMaterias(ArrayList<Materia> materias) {
		ArrayList<String> nombresDeMaterias = new ArrayList<String>();

		for (Materia materia : materias) {
			nombresDeMaterias.add(materia.toString());
		}

		return nombresDeMaterias;
	}

	private Materia MateriaConNombre(String nombreABuscar) {

		for (Materia materia : Materias) {
			if (materia.getNombre().equalsIgnoreCase(nombreABuscar)) {
				return materia;
			}
		}

		return null;
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
		// getMenuInflater().inflate(R.menu.notas_parciales, menu);
		return true;
	}

}
