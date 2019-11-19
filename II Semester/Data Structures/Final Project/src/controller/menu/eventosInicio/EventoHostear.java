package controller.menu.eventosInicio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.menu.MenuPrincipalController;
import controller.menu.MenuSeleccionController;

public class EventoHostear extends MouseAdapter {
	
	MenuPrincipalController controller;
	
	public EventoHostear(MenuPrincipalController pController) {
		controller = pController;
	}
	
	public void mouseClicked(MouseEvent e) {
		controller.getVista().dispose();
		
		MenuSeleccionController vistaNueva = new MenuSeleccionController(controller.getUsuario());
	}
	
}