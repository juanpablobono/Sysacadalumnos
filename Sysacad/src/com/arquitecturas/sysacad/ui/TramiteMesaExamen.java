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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Nombres;

public class TramiteMesaExamen extends ActionBarActivity {

	private EditText textAsignaturas;
	private EditText textObservaciones;
	private String recipient;
	private String subject;
	private Button buttonEnviar;
	private Alumno alumno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tramite_mesa_examen);

		inicializar();
		buttonEnviar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				sendEmail();

			}
		});
	}

	private void inicializar() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor(Nombres.COLOR_ACTIONBAR)));

		alumno = Alumno.getInstance();
		subject = (String) "Solicitud de certificado - Sysacad Movil";
		textAsignaturas = (EditText) findViewById(R.id.textAsignaturas);
		textObservaciones = (EditText) findViewById(R.id.textObservaciones);
		buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
	}


protected void sendEmail() {

		// cambiar mail una vez terminado por
		// certificados_syacad@frsfco.utn.edu.ar
		recipient = (String) "tramites_sysacad@frsfco.utn.edu.ar";

		String[] recipients = { recipient };
		Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
		email.setType("message/rfc822");

		subject = (String) "Tramites desde Sysacad Movil";	 



		email.putExtra(Intent.EXTRA_EMAIL, recipients);
		email.putExtra(Intent.EXTRA_SUBJECT, subject);
		email.putExtra(
				Intent.EXTRA_TEXT,
				"Nombre: " + alumno.getNombre() + "\nApellido: "
						+ alumno.getApellido()
						+ "\nLegajo: "
						+ alumno.getLegajo()
						+ "\nDNI: "
						+ alumno.getDni()
						+ "\nPlan: " + alumno.getPlan() + "\nEspecialidad: "
						+ alumno.getCarrera().toString() + "\nAsignaturas: "
						+ textAsignaturas.getText().toString() + "\nTelefono: "
						+ alumno.getTelefono() + "\nEmail: "
						+ alumno.getEmail() + "\nObservaciones: "
						+ textObservaciones.getText().toString());

		try {
			// el usuario elige el cliente de mail
			startActivity(Intent.createChooser(email,
					"Elija un cliente de email..."));

		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(TramiteMesaExamen.this,
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
		// getMenuInflater().inflate(R.menu.actividades_tramites, menu);
		return true;
	}

}
