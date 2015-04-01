package com.arquitecturas.sysacad.utils;

import android.text.InputFilter;
import android.text.Spanned;

public class Filtros {
	public static InputFilter filtroNumerico() {
		
		InputFilter filtroAlfabetico = new InputFilter() {   
		     @Override  
		     public CharSequence filter(CharSequence arg0, int arg1, int arg2, Spanned arg3, int arg4, int arg5)  
		     {  
		         for (int k = arg1; k < arg2; k++) {   
		             if (Character.isDigit(arg0.charAt(k))) {   
		             return ""; 
		             }   
		         }
		         return null;
		     }
		 };
		 return filtroAlfabetico;
	}
}
