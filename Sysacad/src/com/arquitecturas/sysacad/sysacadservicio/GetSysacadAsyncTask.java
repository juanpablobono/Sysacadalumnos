package com.arquitecturas.sysacad.sysacadservicio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

// Esta clase hace consulta al servicio de sysacad, el primer parametro es el tipo de consulta, 
// el segundo son el nro legajo y el 3ro la contraseñas, estos ultimos si la consulta lo requiere.
public class GetSysacadAsyncTask extends AsyncTask<String, Void, String> {

    private static final String CARACTERSEPARADORDELEGAJOYCONTRASENIA = ":";
    private static final String CARACTERSEPARADORCONSULTA = "/";

    private final String URL_DE_CONSULTA = "http://www.frsfco.utn.edu.ar/sysacadservicio/";

    // Consultas con legajo
    public static final String INGRESO = "ingreso/", CURSADO = "cursado/",
	    ESTADOACADEMICO = "cursado/estadoacademico/",
	    ENCUESTA = "cursado/encuesta/", EXAMENES = "examenes/",
	    MATERIASINSCRIPTAS = "examenes/materiasinscriptas/",
	    MATERIASPARAINSCRIPCION = "examenes/materiasparainscripcion/",
	    FECHASEXAMENES = "examenes/fechas/";

    // Consultas sin Legajo
    public static final String CALENDARIO = "calendario/", PLAN = "plan/";

    @Override
    protected String doInBackground(String... params) {

	try {

	    String tipoDeConsulta = params[0];
	    String urlAConsultar = URL_DE_CONSULTA + tipoDeConsulta;

	    HttpGet httpget;

	    if (NecesitaLoguearse(tipoDeConsulta) && (params.length > 1)) {
		String legajo = params[1];
		String contrasenia = params[2];
		urlAConsultar = urlAConsultar + CARACTERSEPARADORCONSULTA + legajo;
		
		if (FECHASEXAMENES.equals(tipoDeConsulta)) {
		    String especialidad = params[3];
		    String plan = params[4];
		    String materia = params[5];
			urlAConsultar = urlAConsultar + CARACTERSEPARADORCONSULTA
				+ especialidad + CARACTERSEPARADORCONSULTA + plan
				+ CARACTERSEPARADORCONSULTA + materia;
		    }
		
		httpget = new HttpGet(urlAConsultar);
		AgregarAlEncabezadoElLegajoYContrasenia(httpget, legajo,
			contrasenia);
		
	    } else {
		httpget = new HttpGet(urlAConsultar);
	    }
	    /*
	     * httppost.setHeader("Content-Type",
	     * "application/x-www-form-urlencoded;charset=UTF-8");
	     * httppost.setEntity(new
	     * StringEntity("grant_type=client_credentials"));
	     */

	    DefaultHttpClient httpclient = new DefaultHttpClient(
		    new BasicHttpParams());

	    HttpResponse response = httpclient.execute(httpget);
	    HttpEntity entity = response.getEntity();

	    InputStream inputStream = entity.getContent();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
		    inputStream, "UTF-8"), 8);
	    StringBuilder sb = new StringBuilder();

	    String line = null;

	    while ((line = reader.readLine()) != null) {
		sb.append(line + "\n");
	    }

	    return sb.toString();
	    
	} catch (Exception e) {
	    Log.e("GetSysacadAsynTask", "Error:" + e.getMessage());
	    return null;
	}

    }

    /**
     * @param httpget
     * @param legajo
     * @param contrasenia
     */
    private void AgregarAlEncabezadoElLegajoYContrasenia(HttpGet httpget,
	    String legajo, String contrasenia) {
	if (legajo.length() > 0) {
	    String legajoYContrasenia = legajo
		    + CARACTERSEPARADORDELEGAJOYCONTRASENIA + contrasenia;
	    String authorization = "Basic "
		    + Base64.encodeToString(legajoYContrasenia.getBytes(),
			    Base64.NO_WRAP);

	    httpget.setHeader("Authorization", authorization);
	}
    }

    private boolean NecesitaLoguearse(String tipoDeConsulta) {
	boolean necesitaLoguerse;
	if ((tipoDeConsulta.equals(CALENDARIO))
		|| (tipoDeConsulta.equals(PLAN))) {
	    necesitaLoguerse = false;
	} else {
	    necesitaLoguerse = true;
	}
	return necesitaLoguerse;
    }

}
