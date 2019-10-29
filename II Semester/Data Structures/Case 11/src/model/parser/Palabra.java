package model.parser;

import java.util.ArrayList;

public class Palabra implements Comparable {
	
	private String palabra;
	private ArrayList<PalabrasRepetidas> palabras;
	
	public Palabra() {
		palabras = new ArrayList<PalabrasRepetidas>();
	}
	
	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public ArrayList<PalabrasRepetidas> getPalabras() {
		return palabras;
	}

	public void setPalabras(ArrayList<PalabrasRepetidas> palabras) {
		this.palabras = palabras;
	}

//	public int compareTo(String o) {
//		return o.compareTo(palabra);
//	}
	
	public ArrayList<String> getLinks() {
		ArrayList<String> resultado = new ArrayList<String>();
		for (PalabrasRepetidas palabra : palabras) {
			resultado.add(palabra.getSitioWeb());
		}
		return resultado;
	}

	@Override
	public int compareTo(Object o) {
		return ((Palabra)o).getPalabra().compareTo(this.palabra);
	}
	
}
