package com.arquitecturas.sysacad.ui;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Carrera;
import com.arquitecturas.sysacad.logic.Hashtag;
import com.arquitecturas.sysacad.logic.Nombres;
import com.arquitecturas.sysacad.utils.Hashtags;

@SuppressLint("NewApi")
public class Configuracion extends ActionBarActivity {

	private Spinner spinner_carrera;
	private int cantidadMaximaDeAnios = 7;
	private CheckBox[] checks_Anios = new CheckBox[cantidadMaximaDeAnios];
	private Carrera carreraSeleccionada;
	private Alumno alumno;
	private Hashtags hashtags;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuracion);

		inicializar();

		addListenerOnSpinnerItemSelection();

		addSetOnClickListener();

		Bundle extra = getIntent().getExtras();

		if ((extra != null) && (extra.getBoolean(Nombres.ACTUALIZAR))) {
			mostrarDialogo("Por favor, verifica tu carrera y el año de las materias que cursas");
			cargarDatos();
		} else {
			mostrarDialogo("Por favor, selecciona tu carrera y el año de las materias que cursas");
		}

	}

	private void addSetOnClickListener() {
		for (int anio = 1; anio < cantidadMaximaDeAnios; anio++) {

			checks_Anios[anio].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int anioSeleccionado;
					switch (v.getId()) {
					case R.id.check_1:
						anioSeleccionado = 1;
						break;
					case R.id.check_2:
						anioSeleccionado = 2;
						break;
					case R.id.check_3:
						anioSeleccionado = 3;
						break;
					case R.id.check_4:
						anioSeleccionado = 4;
						break;
					case R.id.check_5:
						anioSeleccionado = 5;
						break;
					case R.id.check_6:
						anioSeleccionado = 6;
						break;
					default:
						anioSeleccionado = 0;
						break;
					}

					Hashtag hashtag = new Hashtag(carreraSeleccionada,
							anioSeleccionado);

					if (((CheckBox) v).isChecked()) {
						hashtags.AgregarHashtag(hashtag);
					} else {
						hashtags.EliminarHashtag(hashtag);
					}
				}
			});
		}

	}

	private void addListenerOnSpinnerItemSelection() {
		spinner_carrera.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {

				String nombreCarreraSeleccionada = parent
						.getItemAtPosition(pos).toString();

				if (nombreCarreraSeleccionada != Nombres.SELECCIONAR_CARRERA) {

					CargarAniosDeCarrera(nombreCarreraSeleccionada);

				} else {
					int invisible = 4;
					for (int anio = 1; anio < cantidadMaximaDeAnios; anio++) {
						checks_Anios[anio].setVisibility(invisible);
					}
				}
			}

			/**
			 * @param nombreCarreraSeleccionada
			 */
			private void CargarAniosDeCarrera(String nombreCarreraSeleccionada) {
				carreraSeleccionada = Carrera
						.CarreraConNombre(nombreCarreraSeleccionada);

				int invisible = 4;
				int visible = 0;

				for (int anio = 1; anio < cantidadMaximaDeAnios; anio++) {
					if (anio <= carreraSeleccionada.getCantidadDeAnios()) {
						checks_Anios[anio].setChecked(hashtags
								.ExisteHastagDeLaCarreraParaElAnio(
										carreraSeleccionada, anio));
						checks_Anios[anio].setVisibility(visible);
					} else {
						checks_Anios[anio].setVisibility(invisible);
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}

		});
	}

	private void inicializar() {

		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor(Nombres.COLOR_ACTIONBAR)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		alumno = Alumno.getInstance();

		spinner_carrera = (Spinner) findViewById(R.id.spinner_carreras);

		checks_Anios[1] = (CheckBox) findViewById(R.id.check_1);
		checks_Anios[2] = (CheckBox) findViewById(R.id.check_2);
		checks_Anios[3] = (CheckBox) findViewById(R.id.check_3);
		checks_Anios[4] = (CheckBox) findViewById(R.id.check_4);
		checks_Anios[5] = (CheckBox) findViewById(R.id.check_5);
		checks_Anios[6] = (CheckBox) findViewById(R.id.check_6);

		Carrera.crearCarreras();
		List<String> carreras = Carrera.getNombresDeCarreras();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, carreras);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_carrera.setAdapter(dataAdapter);

		hashtags = new Hashtags(this);

	}

	private void cargarDatos() {
		alumno = Alumno.getInstance();
		spinner_carrera.setSelection(alumno.getCarrera().getId());
	}

	private void mostrarDialogo(String mensaje) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				Configuracion.this);
		builder.setTitle(R.string.notificaciones);
		builder.setMessage(mensaje);
		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
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
	protected void onResume() {
		super.onResume();

		if (MainActivity.isUsuarioLogeado()) {
			getSupportActionBar().setSubtitle(
					"Identificado como " + alumno.getNombre() + " "
							+ alumno.getApellido());
		} else {
			getSupportActionBar().setSubtitle("Ninguna sesi�n iniciada");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.carreras, menu);
		return true;
	}

}
