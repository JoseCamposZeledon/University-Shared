package model.ubicacion;

public class Barrio extends Ubicacion{
	
	public Barrio() {}
	
	public Barrio(String pNombre) {
		super(pNombre);
	}
	
	public String toString() {
		return "BARRIO: " + nombre;
	}
}
