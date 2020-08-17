package juegovida.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import juegovida.modelo.Celda;

public class Vista extends JFrame{
	private static final Dimension TAMANO_TABLERO = new Dimension(666,666);
	private static final Dimension TAMANO_MENU = new Dimension(TAMANO_TABLERO.height, 60);
	
	private static final long serialVersionUID = 1L;
	private VistaTablero tablero;
	private MenuInferior menu;

	public Vista() {
		setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		tablero = new VistaTablero(TAMANO_TABLERO);
		this.add(tablero, BorderLayout.PAGE_START);
		menu = new MenuInferior(TAMANO_MENU);
		this.add(menu, BorderLayout.PAGE_END);
		this.pack();
	}
	
	
	public MenuInferior getMenu() {
		return menu; 
	}
	
	public VistaTablero getVistaTablero() {
		return tablero;
	}
	
	public void actualizarVista(int generacion, int vivas, int muertas, Celda[][] matriz) {
		this.tablero.actualizarTablero(matriz);
		this.menu.setGeneracion(generacion);
		this.menu.setVivas(vivas);
		this.menu.setMuertas(muertas);
	}
}
