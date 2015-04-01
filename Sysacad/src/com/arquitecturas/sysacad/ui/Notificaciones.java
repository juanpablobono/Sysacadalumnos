package com.arquitecturas.sysacad.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arquitecturas.sysacad.R;
import com.arquitecturas.sysacad.logic.Alumno;
import com.arquitecturas.sysacad.logic.Nombres;
import com.arquitecturas.sysacad.logic.Notificacion;
import com.arquitecturas.sysacad.twitter.GetBearerTokenAsyncTask;
import com.arquitecturas.sysacad.twitter.GetFeedAsyncTask;
import com.arquitecturas.sysacad.utils.Dialogo;
import com.arquitecturas.sysacad.utils.Hashtags;

public class Notificaciones extends ActionBarActivity {

	private String[] hashtagsABuscar = { "" };
	private final String USUARIO = "Sysacad_SanFco";
	private ListView listViewResultados;
	private ArrayList<Notificacion> notificacionesOk = null;
	private ArrayList<String> notificacionesOkString = null;
	private String jsonResultado = null;
	private String mAccessToken;
	private Alumno alumno;

	// ------------------------------ON CREATE---------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notificaciones);

		inicializar();

		listViewResultados = (ListView) findViewById(R.id.lista_notificaciones);

		Hashtags hashtags = new Hashtags(this);

		hashtagsABuscar = hashtags.getHashtags();

		if (conexionActiva()) {
			new GetBearerTokenAsyncTask() {
				
				@Override
				protected void onPreExecute() {
					ocultarControles();
				}

				@Override
				protected void onPostExecute(String jsonText) {
					super.onPostExecute(jsonText);

					try {

						JSONObject root = new JSONObject(jsonText);
						mAccessToken = (String) root.getString("access_token");

						BuscarTwitter();

					} catch (Exception e) {
						Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
					}
				}

			}.execute();
		} else {
			Dialogo.mostrarMensaje(R.string.notificaciones,
					"No hay conexi�n de red", Notificaciones.this);
		}
	}

	private void inicializar() {
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor(Nombres.COLOR_ACTIONBAR)));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		alumno = Alumno.getInstance();
	}

	private boolean conexionActiva() {
		ConnectivityManager administradorConexion = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = administradorConexion
				.getActiveNetworkInfo();

		return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
	}

	// ------------------------------------METODOS---------------------------------------

	public void BuscarTwitter() {

		// URls de ejemplo para busqueda por hashtags o por usuarios,
		// respectuvamente:
		// https://api.twitter.com/1.1/search/tweets.json?q=%23MiSerieFAvorita&result_type=recent&count=2
		// https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=usuario&count=2

		String cantidadResultados = "150";

		String httpABuscar = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="
				+ USUARIO + "&count=";

		if (cantidadCorrecta(cantidadResultados)) {
			httpABuscar = httpABuscar + cantidadResultados;
		} else {
			httpABuscar = httpABuscar + "1";
		}

		new GetFeedAsyncTask() {
			
			@Override
			protected void onPostExecute(String jsonText) {

				try {

					jsonResultado = jsonText;

				} catch (Exception e) {
					Log.e("GetFeedTask", "Error:" + e.getMessage());
				} finally {

					generarNotificacionesAMostrar();

					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							getApplicationContext(), R.layout.layout_listas,
							notificacionesOkString);

					listViewResultados.setAdapter(adapter);
					
					mostrarControles();
				}
			}

		}.execute(mAccessToken, httpABuscar);
	}

	public boolean cantidadCorrecta(String cantidadResultados) {

		boolean entre1y200 = false;

		try {
			if (cantidadResultados != "") {

				int cantidad = Integer.parseInt(cantidadResultados);

				if (cantidad >= 1 && cantidad <= 200) {

					entre1y200 = true;
				}
			}
		} catch (Exception e) {
			Log.e("cantidadCorrecta", "Error:" + e.getMessage());

		}

		return entre1y200;
	}

	public void generarNotificacionesAMostrar() {

		notificacionesOk = notificacionesParseadas();

		notificacionesOkString = new ArrayList<String>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				Locale.ENGLISH);

		for (int i = 0; i < notificacionesOk.size(); i++) {

			Date fechaYHora = notificacionesOk.get(i).getFechaYHora();
			String srtFechaYHora = sdf.format(fechaYHora);

			notificacionesOkString.add(srtFechaYHora + ":\n "
					+ notificacionesOk.get(i).getTexto());
		}
	}

	public ArrayList<Notificacion> notificacionesParseadas() {

		ArrayList<Notificacion> listaNotificaciones = new ArrayList<Notificacion>();

		try {

			if (jsonResultado != null) {

				JSONArray arrayGeneral = new JSONArray(jsonResultado);

				for (int i = 0; i < arrayGeneral.length(); i++) {
					
					JSONObject objetoTweet = arrayGeneral.getJSONObject(i);
					JSONArray arrayHashtags = objetoTweet.getJSONObject("entities").getJSONArray("hashtags");
					
					ArrayList<String> hashtagsTweet = new ArrayList<String>();
					
					for (int j = 0; j < arrayHashtags.length(); j++) {
						
						JSONObject hashtagUsado = arrayHashtags.getJSONObject(j);
						hashtagsTweet.add(hashtagUsado.getString("text"));
					}

					if (objetoTweet.getString("text") != null) {

						if (tieneHashtagBuscado(hashtagsTweet)) {

							Notificacion nuevaNotificacion = new Notificacion();

							nuevaNotificacion.setTexto(objetoTweet.getString("text"));
							nuevaNotificacion.setFechaYHoraString(objetoTweet.getString("created_at"));

							Date fechaYHora = fechaYHoraParseada(objetoTweet.getString("created_at"));
							nuevaNotificacion.setFechaYHora(fechaYHora);
							
							nuevaNotificacion.setIdTweet(objetoTweet.getString("id_str"));

							listaNotificaciones.add(nuevaNotificacion);
						}
					}
				}

				if (listaNotificaciones.size() == 0) {
					Dialogo.mostrarMensaje(R.string.notificaciones,
							"No hay datos para mostrar", Notificaciones.this);
				}

			} else {
				Dialogo.mostrarMensaje(R.string.notificaciones,
						"No hay datos para mostrar", Notificaciones.this);
			}
		}

		catch (Exception e) {
			Log.e("notificacionesParseadas", "Error:" + e.getMessage());
		}

		return listaNotificaciones;
	}

	public boolean tieneHashtagBuscado(ArrayList<String> hashtagsTweet) {

		boolean buscado = false;

		for (int i = 0; i < hashtagsTweet.size(); i++) {
			
			for (int j = 0; j < hashtagsABuscar.length; j++) {
				if(hashtagsTweet.get(i).equals(hashtagsABuscar[j])){
					buscado = true;
				}
			}
		}

		return buscado;
	}

	public Date fechaYHoraParseada(String fechaYHoraTwitter) {

		Date fechaYHora = null;

		final String TWITTER_DATE_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";

		SimpleDateFormat sdf = new SimpleDateFormat(TWITTER_DATE_FORMAT,
				Locale.ENGLISH);
		sdf.setLenient(true);

		try {

			fechaYHora = sdf.parse(fechaYHoraTwitter);
		} catch (java.text.ParseException e) {

			e.printStackTrace();
		}

		return fechaYHora;
	}

	private void ocultarControles(){
		findViewById(R.id.lista_notificaciones).setVisibility(View.INVISIBLE);
		findViewById(R.id.progressBar_notificaciones).setVisibility(View.VISIBLE);
		findViewById(R.id.text_cargando).setVisibility(View.VISIBLE);
	}
	
	private void mostrarControles(){
		findViewById(R.id.lista_notificaciones).setVisibility(View.VISIBLE);
		findViewById(R.id.progressBar_notificaciones).setVisibility(View.INVISIBLE);
		findViewById(R.id.text_cargando).setVisibility(View.INVISIBLE);
	}
	
	// ---------------------------- FIN METODOS--------------------------------

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
		// getMenuInflater().inflate(R.menu.notificaciones, menu);
		return true;
	}

}
