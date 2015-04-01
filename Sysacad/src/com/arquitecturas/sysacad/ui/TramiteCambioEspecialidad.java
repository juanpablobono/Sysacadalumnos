package com.arquitecturas.sysacad.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Carrera;
import com.arquitecturas.sysacad.logic.Nombres;

public class TramiteCambioEspecialidad extends ActionBarActivity {

	private Spinner spinner_carreras;
	private EditText observaciones;
	private String recipient;
	private String subject;
	private Button sendBtn;

	private Alumno alumno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tramite_cambio_especialidad);

		inicializar();
		sendBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				sendEmail();

			}
		});
	}

	private void inicializar() {
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor(Nombres.COLOR_ACTIONBAR)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		alumno = Alumno.getInstance();

		spinner_carreras = (Spinner) findViewById(R.id.spinner_carreras_cambio_especialidad);
		observaciones = (EditText) findViewById(R.id.textObservaciones);
		sendBtn = (Button) findViewById(R.id.buttonEnviar);

		spinner_carreras.setAdapter(new ArrayAdapter<String>(this,
				R.layout.layout_listas, Carrera
						.getNombresDeCarrerasCambioEspecialidad()));
	}

	protected void sendEmail() {

		recipient = (String) "tramites_sysacad@frsfco.utn.edu.ar";
		String[] recipients = { recipient };
		Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
		email.setType("message/rfc822");
		subject = (String) "Tramites desde Sysacad Movil";
		email.putExtra(Intent.EXTRA_EMAIL, recipients);
		email.putExtra(Intent.EXTRA_SUBJECT, subject);
		email.putExtra(
				Intent.EXTRA_TEXT,
				"Nombre: "
						+ alumno.getNombre()
						+ "\n Apellido: "
						+ alumno.getApellido()
						+ "\nLegajo: "
						+ alumno.getLegajo()
						+ "\nDNI: "
						+ alumno.getDni()
						+ "\nEspecialidad: "
						// podria cambiar getCarrera por otro metodo m�s
						// adelante
						+ alumno.getCarrera().getNombre() + "\nPlan: "
						+ alumno.getPlan() + "\nTel�fono: "
						+ alumno.getTelefono() + "\nE-mail: "
						+ alumno.getEmail() + "\nEspecialidad Destino: "
						+ spinner_carreras.getSelectedItem().toString()
						+ "\nObservaciones: "
						+ observaciones.getText().toString());

		try {
			startActivity(Intent.createChooser(email,
					"Elija un cliente de email..."));

		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(TramiteCambioEspecialidad.this,
					"No hay cliente de eMail instalado.", Toast.LENGTH_LONG)
					.show();
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
		// getMenuInflater().inflate(R.menu.tramite_cambio_especialidad, menu);
		return true;
	}

}
