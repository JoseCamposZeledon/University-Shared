package model.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import model.jsonReader.JsonParser;
import model.tree.AVLTree;

public class Parser implements ParserConstant {
	
	private static Parser instance;
	
	private int ancho;
	private int profundidad;
	private Queue<Sitio> sitios;
	private SitioFactory factory;
	private AVLTree avlPalabras;
	private ArrayList<AVLTree> listaSitiosPalabras;
	
	private Parser() throws InstantiationException, IllegalAccessException {
		this.ancho = JsonParser.get().getData().getAncho();
		this.profundidad = JsonParser.get().getData().getProfundidad();
		sitios = new LinkedList<Sitio>();
		for (String valor : JsonParser.get().getData().getLinks()) {
			sitios.add(new Sitio(valor));
		}
		factory = new SitioFactory();
		avlPalabras = new AVLTree();
		listaSitiosPalabras = new ArrayList<AVLTree>();
	}
	
	public static Parser get() throws InstantiationException, IllegalAccessException {
		if (instance == null) {
			instance = new Parser();
		}
		return instance;
	}
	
	public void computeSites() {
		HashMap<String, String> map;
		int counterSitios = 1;
		int iteraciones = 1;
		Document document;
		while (!sitios.isEmpty() && iteraciones <= SITE_LIMIT) {
			Sitio current = sitios.poll();
			System.out.println(current.getLink() + " " + iteraciones);
			try {
				iteraciones++;
				if (counterSitios <= SITE_LIMIT){
					document = Jsoup.connect(current.getLink()).timeout(SITE_TIMEOUT * 1000).get();
					if (document.hasText()) {
						for (Element e : document.select("a")) {
							String link = e.attr("href");
							if (link.startsWith("https://") && current.getAnchuraActual() < 10 && current.getProfundidadActual() < 3) {
								sitios.add(new Sitio(current.getProfundidadActual() + 1, 0, link));
								current.incrementarAnchura();
								counterSitios++;
							}
							// Borra el link para que no aparezca 
							e.text("");
						}
						// agregar a los arboles
					}
				}
			} catch (IOException e1) {
				// Timeout al conectarse
				System.out.println("Timeout en: " + current.getLink() + " " + iteraciones);
			}
		}
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
		Parser.get().computeSites();
	}
	
	
}
