package com.arquitecturas.sysacad.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Nombres;
import com.arquitecturas.sysacad.utils.Filtros;

public class TramitePaseFacultad extends ActionBarActivity {

	private EditText razones;
	private EditText facultadDestino;
	private EditText especialidadDestino;

	private String recipient;
	private String subject;
	private Button sendBtn;

	private Alumno alumno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tramite_pase_facultad);
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

		razones = (EditText) findViewById(R.id.textRazones);
		facultadDestino = (EditText) findViewById(R.id.textFacultadDestino);
		especialidadDestino = (EditText) findViewById(R.id.textEspecialidadDestino);

		facultadDestino
				.setFilters(new InputFilter[] { Filtros.filtroNumerico() });
		especialidadDestino.setFilters(new InputFilter[] { Filtros
				.filtroNumerico() });
		sendBtn = (Button) findViewById(R.id.buttonEnviar);
	}

	protected void sendEmail() {

		recipient = (String) "tramites_sysacad@frsfco.utn.edu.ar";
		String[] recipients = { recipient };
		Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
		email.setType("message/rfc822");
		subject = (String) "Tramites desde Sysacad Movil";
		email.putExtra(Intent.EXTRA_EMAIL, recipients);
		email.putExtra(Intent.EXTRA_SUBJECT, subject);
		email.putExtra(Intent.EXTRA_TEXT, "Nombre: " + alumno.getNombre()
				+ "\n Apellido: " + alumno.getApellido() + "\nLegajo: "
				+ alumno.getLegajo() + "\nDNI: " + alumno.getDni()
				+ "\nTelefono: " + alumno.getTelefono() + "\nEspecialidad: "
				+ alumno.getCarrera().getNombre() + "\nFacultad de destino: "
				+ facultadDestino.getText().toString()
				+ "\nEspecialidad de destino: "
				+ especialidadDestino.getText().toString() + "\nRazones: "
				+ razones.getText().toString());

		try {
			startActivity(Intent.createChooser(email,
					"Elija un cliente de email..."));

		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(TramitePaseFacultad.this,
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
		// getMenuInflater().inflate(R.menu.tramite_pase_facultad, menu);
		return true;
	}

}
