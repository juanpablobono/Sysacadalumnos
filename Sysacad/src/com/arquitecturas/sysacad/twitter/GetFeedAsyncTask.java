package com.arquitecturas.sysacad.twitter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import com.arquitecturas.sysacad.R;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

public class GetFeedAsyncTask extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) {

		try {
			DefaultHttpClient httpclient = new DefaultHttpClient(
					new BasicHttpParams());

			HttpGet httpget = new HttpGet(params[1]);
			httpget.setHeader("Authorization", "Bearer " + params[0]);
			httpget.setHeader("Content-type", "application/json");

			InputStream inputStream = null;
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			inputStream = entity.getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(inputStream, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			return sb.toString();

		} catch (Exception e) {
			Log.e("GetFeedTask", "Error:" + e.getMessage());
			return null;
		}
	}
}
