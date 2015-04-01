package com.arquitecturas.sysacad.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.arquitecturas.sysacad.logic.Carrera;
import com.arquitecturas.sysacad.logic.Hashtag;

public class Hashtags {

    private static final String CARACTER_SEPARADOR = ",";
    private static final String preferencia = "Hashtags";

    private static String llaveDeHastags = "hashtagABuscar";

    private List<String> hashtags = new ArrayList<String>();
    private SharedPreferences sharedpreferences;

    public Hashtags(Context context) {

	sharedpreferences = context.getSharedPreferences(preferencia,
		Context.MODE_PRIVATE);
	String hashtagGuardados = sharedpreferences.getString(llaveDeHastags,
		"");
	String[] hashtagsEnArray = hashtagGuardados.split(CARACTER_SEPARADOR);
	enlistarArregloDeHashtag(hashtagsEnArray);

    }

    public void AgregarHashtag(Hashtag hashtag) {
	if (!existeHashtagConIdentificador(hashtag.toString())) {
	    hashtags.add(hashtag.toString());
	}
	boolean existeHashtagParaLaCarrera = existeHashtagConIdentificador(hashtag
		.Carrera());
	if (!existeHashtagParaLaCarrera) {
	    hashtags.add(hashtag.Carrera());
	}

	boolean existeHashtagParaLaUniversidad = existeHashtagConIdentificador(hashtag
		.Universidad());
	if (!existeHashtagParaLaUniversidad) {
	    hashtags.add(hashtag.Universidad());
	}
	guardarHashtags();
    }

    /**
	 * 
	 */
    private void guardarHashtags() {
	Editor editor = sharedpreferences.edit();
	editor.putString(llaveDeHastags, HashtagEnPalabra());
	editor.commit();
    }

    private void enlistarArregloDeHashtag(String[] hastagsAEnlistar) {
	for (int posicion = 0; posicion < hastagsAEnlistar.length; posicion++) {
	    hashtags.add(hastagsAEnlistar[posicion].toString());
	}
    }

    private String HashtagEnPalabra() {
	String hashtagEnPalabra = "";

	for (String hashtag : hashtags) {
	    hashtagEnPalabra = hashtagEnPalabra + hashtag + CARACTER_SEPARADOR;
	}
	return hashtagEnPalabra;
    }

    public void EliminarHashtag(Hashtag hashtag) {
	hashtags.remove(hashtag.toString());
	if (CantidadDelHashtasDeLaCarrera(hashtag) == 0) {
	    hashtags.remove(hashtag.Carrera());
	}
	guardarHashtags();
    }

    public boolean existeHashtagConIdentificador(String identificador) {
	boolean existeHashtag = false;

	for (String hashtag : hashtags) {
	    if (hashtag.equalsIgnoreCase(identificador)) {
		existeHashtag = true;
	    }
	}
	return existeHashtag;
    }

    public boolean ExisteHastagDeLaCarreraParaElAnio(Carrera carreraParaBuscar,
	    int anioParaBuscar) {

	Hashtag hashtag = new Hashtag(carreraParaBuscar, anioParaBuscar);

	return existeHashtagConIdentificador(hashtag.toString());
    }

    public String[] getHashtags() {
	String[] hashtagsArray = new String[hashtags.size()];
	return hashtags.toArray(hashtagsArray);
    }

    private int CantidadDelHashtasDeLaCarrera(Hashtag HashtagDecarreraABuscar) {
	int cantidad = 0;
	Carrera carreraABuscar = HashtagDecarreraABuscar.getCarrera();
	int cantidadDeaniosDeCarrera = carreraABuscar.getCantidadDeAnios();
	for (int anio = 0; anio < cantidadDeaniosDeCarrera; anio++) {
	    Hashtag hashtagABuscar = new Hashtag(carreraABuscar, anio);
	    if (existeHashtagConIdentificador(hashtagABuscar.toString())) {
		cantidad++;
	    }
	}

	return cantidad;
    }

}
