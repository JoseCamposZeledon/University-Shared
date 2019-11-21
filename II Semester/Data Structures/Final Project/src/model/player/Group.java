package model.player;

import java.util.LinkedList;
import java.util.Random;

import model.personajes.Archer;
import model.personajes.Brawler;
import model.personajes.Knight;
import model.personajes.Personaje;

public class Group {
	
	private LinkedList<Personaje> personajes;
	private int vidaTeam;
	Random r;
	
	public Group() {
		personajes = new LinkedList<Personaje>();
		vidaTeam = 0;
		r = new Random();
	}
	
	public LinkedList<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(LinkedList<Personaje> personajes) {
		this.personajes = personajes;
	}

	public int getVidaTeam() {
		return vidaTeam;
	}

	public void setVidaTeam(int vidaTeam) {
		this.vidaTeam = vidaTeam;
	}
	
	public void agregarPersonaje(Personaje pPersonaje) throws IndexOutOfBoundsException {
		if (personajes.size() == 4) {
			throw new IndexOutOfBoundsException();
		}
		personajes.add(pPersonaje);
		vidaTeam += 100;
	}
	
	public int danoPorMedioSegundo() {
		int total = 0;
		for (Personaje personaje : personajes) {
			total += personaje.getAtaque().ataque();
		}
		return total;
	}
	
	public boolean restarVida(int vida) {
		vidaTeam -= vida;
		if (vida <= 0) {
			return true;
		} 
		while (((vidaTeam-1) / 100) + 1 < personajes.size()) {
			int index = r.nextInt(personajes.size());
			personajes.remove(index);
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		Group g = new Group();
		g.agregarPersonaje(new Archer());
		g.agregarPersonaje(new Brawler());
		g.agregarPersonaje(new Knight());
		g.agregarPersonaje(new Archer());
		
		System.out.println(g.getVidaTeam());
		System.out.println(g.danoPorMedioSegundo());
		System.out.println(g.danoPorMedioSegundo());
		g.restarVida(100);
		System.out.println(g.danoPorMedioSegundo());
		System.out.println(g.danoPorMedioSegundo());
		System.out.println(g.danoPorMedioSegundo());
		System.out.println(g.danoPorMedioSegundo());
		
	}
}
