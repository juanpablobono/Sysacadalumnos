package com.arquitecturas.sysacad.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Nombres;
import com.arquitecturas.sysacad.sysacadservicio.GetSysacadAsyncTask;
import com.arquitecturas.sysacad.utils.Dialogo;
import com.arquitecturas.sysacad.utils.LectorJSONlogin;

public class Login extends ActionBarActivity {

	private Button but_enviar;
	private EditText text_legajo;
	private EditText text_pass;
	private Alumno alumno;
	private final int ID_NOTAS_PARCIALES = 0;
	private final int ID_CONSULTAS = 1;
	private boolean usuarioLogueado = false;
	private Intent in = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		inicializar();
		
		if(alumno.getLegajo() != 0){
			text_legajo.setText(Integer.toString(alumno.getLegajo()));
			text_pass.requestFocus();
		}
		else{
			text_legajo.requestFocus();			
		}
		
		but_enviar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (!text_legajo.getText().toString().matches("") &&
						!text_pass.getText().toString().matches("")) {
					loguear();
				}
				else {
					Dialogo.mostrarMensaje(R.string.title_activity_login, "Por favor, completa ambos campos correctamente", Login.this);
				}
			}

		});
	}
	
	private void redireccionar() {
		if (getIntent().getExtras().getInt(Nombres.SIGUIENTE) == ID_NOTAS_PARCIALES) {
			in = new Intent(Login.this, NotasParciales.class);
		} else if (getIntent().getExtras().getInt(Nombres.SIGUIENTE) == ID_CONSULTAS) {
			in = new Intent(Login.this, Consultas.class);
		}
	}
	
	private void ocultarControles() {
		findViewById(R.id.text_version).setVisibility(View.INVISIBLE);
		findViewById(R.id.login_legajo).setVisibility(View.INVISIBLE);
		findViewById(R.id.login_password).setVisibility(View.INVISIBLE);
		findViewById(R.id.login_boton_enviar).setVisibility(View.INVISIBLE);
		findViewById(R.id.text_ingresando).setVisibility(View.VISIBLE);
		findViewById(R.id.progress).setVisibility(View.VISIBLE);
	}
	
	private void mostrarControles() {
		findViewById(R.id.text_version).setVisibility(View.VISIBLE);
		findViewById(R.id.login_legajo).setVisibility(View.VISIBLE);
		findViewById(R.id.login_password).setVisibility(View.VISIBLE);
		findViewById(R.id.login_boton_enviar).setVisibility(View.VISIBLE);
		findViewById(R.id.text_ingresando).setVisibility(View.INVISIBLE);
		findViewById(R.id.progress).setVisibility(View.INVISIBLE);		
	}
	
	private boolean loguear() {	
		
		new GetSysacadAsyncTask() {
			@Override
			protected void onPreExecute() {
				ocultarControles();
			}
			protected void onPostExecute(String result) {				
				super.onPostExecute(result);
				
				System.out.println("result: " + result);

				try {
					if (LectorJSONlogin.leerEstadoLogin(result).equalsIgnoreCase(Nombres.USUARIO_VALIDADO)) {
						usuarioLogueado = true;
					}
					else {
						usuarioLogueado = false;
					}
				} 
				catch (Exception e) {
				    Log.e("GetLoginTask", "Error:" + e.getMessage());
				}
				
				if (usuarioLogueado) {
					alumno.setLegajo(Integer.parseInt(text_legajo.getText().toString()));
					alumno.setPassword(text_pass.getText().toString());
					redireccionar();
					startActivity(in);
				}
				else {
					Dialogo.mostrarMensaje(R.string.title_activity_login, "Nombre de usuario y/o contraseņa incorrectos", Login.this);
					mostrarControles();
				}
			}
		}.execute(GetSysacadAsyncTask.INGRESO, text_legajo.getText().toString(), text_pass.getText().toString());
		
		return usuarioLogueado;
	}		

	private void inicializar() {
		
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(Nombres.COLOR_ACTIONBAR)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		but_enviar = (Button) findViewById(R.id.login_boton_enviar);
		text_legajo = (EditText) findViewById(R.id.login_legajo);
		text_pass = (EditText) findViewById(R.id.login_password);
		alumno = Alumno.getInstance();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case android.R.id.home:
	    		//NavUtils.navigateUpFromSameTask(this);
	    		finish();
	    		return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
}
