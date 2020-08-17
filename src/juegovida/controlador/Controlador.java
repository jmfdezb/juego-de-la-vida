package juegovida.controlador;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import juegovida.modelo.Celda;
import juegovida.modelo.Modelo;
import juegovida.vista.Vista;
import juegovida.vista.Boton;

public class Controlador implements ActionListener, ChangeListener {
	private static Vista vista;
	private static Modelo modelo;
	private static boolean ejecutando = false;
	private static Timer temporizador;
	private static int numeroCelulas = 9;
	private static int porcentajeVida = 50;
	
	public Controlador(Modelo modelo, Vista vista) {
		Controlador.modelo = modelo;
		Controlador.vista = vista;
		((Boton)obtenerComponente("BotonStart")).setAction(new AccionPulsacion("Arrancar", this));
		((Boton)obtenerComponente("BotonReset")).setAction(new AccionPulsacion("Resetear", this));
		((JSlider)obtenerComponente("SlideNumeroCelulas")).addChangeListener(this);
		((JSlider)obtenerComponente("SlidePorcentajeVivas")).addChangeListener(this);
		actualizarVista();
		vista.setVisible(true);
		temporizador = new Timer(40, this);
	}
	
	private Component obtenerComponente(String nombre) {
		for (Component c : vista.getMenu().getComponents()) {
			if (c.getName() == nombre) {
				return c;
			}
		}
		return null;
		
	}
	
	private void actualizarVista() {
		vista.actualizarVista(Modelo.getGeneracion(), Modelo.getVivas(), Modelo.getMuertas(), Modelo.getTablero());
	}
	
	protected void reset() {
		temporizador.stop();
		Modelo.reset(numeroCelulas, porcentajeVida);
		actualizarVista();
		estadoMenu();
	}
	
	public void iteracion() {
		int generacion = Modelo.getGeneracion();
		modelo.iteracion();
		if ( generacion == Modelo.getGeneracion() ) { ejecutar(false); }
	}
	
	public Celda[][] getUniverso() {
		return Modelo.getTablero();
	}
	
	private void estadoMenu() {
		vista.getMenu().setEjecutando(ejecutando);
		if (ejecutando) {
			((Boton)obtenerComponente("BotonStart")).setAction(new AccionPulsacion("Pausar", this));
			((Boton)obtenerComponente("BotonStart")).setEtiqueta("Pausar");
		} else {
			if (Modelo.getGeneracion() > 1) {
				((Boton)obtenerComponente("BotonStart")).setAction(new AccionPulsacion("Reanudar", this));
				((Boton)obtenerComponente("BotonStart")).setEtiqueta("Reanudar");
			} else {
				((Boton)obtenerComponente("BotonStart")).setAction(new AccionPulsacion("Arrancar", this));
				((Boton)obtenerComponente("BotonStart")).setEtiqueta("Arrancar");
			}
		}
	}
	
	public void ejecutar(boolean ejecutando) {
		Controlador.ejecutando = ejecutando;
		estadoMenu();
		if (ejecutando) { temporizador.restart(); } else {temporizador.stop(); }
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		iteracion();
		actualizarVista();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider origen = (JSlider)e.getSource();
		if (origen.getName() == "SlideNumeroCelulas") {
			
			int porcentaje = origen.getValue();
			if (porcentaje < 16) { Controlador.numeroCelulas = 9; }
			else if (porcentaje < 33 ) { Controlador.numeroCelulas = 18; }
			else if (porcentaje < 50 ) { Controlador.numeroCelulas = 37; }
			else if (porcentaje < 66 ) { Controlador.numeroCelulas = 74; }
			else if (porcentaje < 85 ) { Controlador.numeroCelulas = 111; }
			else { Controlador.numeroCelulas = 222; }
			
		}
		if (origen.getName() == "SlidePorcentajeVivas") { Controlador.porcentajeVida = origen.getValue();};
		reset();
	}

	public void resetSliders() {
		vista.getMenu().resetSliders();
		vista.getMenu().habilitarSliders();
	}
}
