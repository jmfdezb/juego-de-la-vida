package juegovida;

import javax.swing.SwingUtilities;

import juegovida.controlador.Controlador;
import juegovida.modelo.Modelo;
import juegovida.vista.Vista;

public class Principal {
	
//	private static Controlador controlador = new Controlador();
//	private static Vista ventana;

	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");
		SwingUtilities.invokeLater(new Runnable() { public void run() { ejecutarInterfaz(); } });
	}
	
	private static void ejecutarInterfaz() {
		new Controlador(new Modelo(18), new Vista());
	}
}
