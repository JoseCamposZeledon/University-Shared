package model.arbolNArio;

import java.util.ArrayList;

public class ArbolNArio<T> {

	private NodoNArio<T> raiz;
	private int cantidadNodos;
	
	
	// Constructores
	
	public ArbolNArio() {
		raiz = null;
	}
	
	public ArbolNArio(T pValor) {
		raiz = new NodoNArio<T>();
		raiz.setValor(pValor);
		
		cantidadNodos += 1;
	}

	// Getters & Setters generados
	public NodoNArio<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoNArio<T> raiz) {
		this.raiz = raiz;
	}
	
	public int getCantidadNodos() {
		return cantidadNodos;
	}
	
	//Metodos creados
	
	public void agregarNodo(NodoNArio<T> pPadre, NodoNArio<T> pNodo) {
		
		pPadre.agregarHijo(pNodo);
		
		if (pNodo.tieneHijos()) cantidadNodos += pNodo.getHijos().size();
		
		cantidadNodos += 1;
		
	}
	
	public void quitarNodo(NodoNArio<T> pNodo) {
		// Quita el nodo siempre que no sea la raiz
		if (pNodo.getPadre() != null) { 
			
			NodoNArio<T> padre = pNodo.getPadre();
			ArrayList<NodoNArio<T>> hijos = pNodo.getHijos();
			
			pNodo = null;
			
			hijos.forEach((hijoActual) -> hijoActual.setPadre(padre));
		}
		
	}
	
	public boolean isEmpty() {
		return raiz == null;
	}
}
