package com.arquitecturas.sysacad.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

public class GetBearerTokenAsyncTask extends AsyncTask<Void, Void, String> {

	private static final String HTTPS_DE_CONSULTA_DE_TOKEN = "https://api.twitter.com/oauth2/token";
	private final String APIKEY = "T1iHE66S6uVxrpGnbcaJtULTc";
	private final String APISECRET = "xiFdN72P59mcs3DOYtnOVdkjnjf4HGPxOR4FUyocJRy9zl7eZL";

	
	@Override
	protected String doInBackground(Void... params) {

		HttpEntity respuestaDeLaPagina = RespuestaParaLaPagina(PaginaAConsultar());
		return JsonParaLaRespuestaDePagina(respuestaDeLaPagina);
	}

	/**
	 * Esta rutina me devuelve un json de la respuesta de la pagina
	 */
	private String JsonParaLaRespuestaDePagina(HttpEntity respuestaDeLaPagina) {

		try {
			InputStream inputStream = respuestaDeLaPagina.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"), 8);
			StringBuilder palabra = new StringBuilder();

			String linea = null;

			while ((linea = reader.readLine()) != null) {
				palabra.append(linea + "\n");
			}

			return palabra.toString();

		} catch (Exception e) {
			Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
			return null;
		}
	}

	/**
	 * Esta rutina me devuelve el resultado de la consultar a la pagina
	 */
	private HttpEntity RespuestaParaLaPagina(HttpPost httpAConsultar) {
		DefaultHttpClient httpclient = new DefaultHttpClient(
				new BasicHttpParams());

		// Respuesta de la http consultada
		HttpResponse respuesta;
		try {
			respuesta = httpclient.execute(httpAConsultar);
		} catch (Exception e) {
			Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
			respuesta = null;
		}
		HttpEntity entity = respuesta.getEntity();
		return entity;
	}

	/**
	 * Esta rutina devuelve la pagina de consulta del token
	 */
	private HttpPost PaginaAConsultar() {
		HttpPost httppost = new HttpPost(HTTPS_DE_CONSULTA_DE_TOKEN);

		// Encabezado de pagina
		httppost.setHeader("Authorization", Autorizacion());
		httppost.setHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		try {
			httppost.setEntity(new StringEntity("grant_type=client_credentials"));
		} catch (Exception e) {
			Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
		}
		return httppost;
	}

	/**
	 * Esta rutina devuelve una codigo de autorizacion
	 */
	private String Autorizacion() {
		String apiKeyYApiSecret = APIKEY + ":" + APISECRET;
		byte[] apiKeyYApiSecretEnBytes = apiKeyYApiSecret.getBytes();
		String codigoAutorizacion = "Basic "
				+ Base64.encodeToString(apiKeyYApiSecretEnBytes, Base64.NO_WRAP);
		return codigoAutorizacion;
	}
}