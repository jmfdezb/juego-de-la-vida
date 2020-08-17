package juegovida.controlador;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class AccionPulsacion extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Controlador controlador;

	public AccionPulsacion(String nombre, Controlador controlador) {
		this.controlador = controlador;
		putValue(Action.NAME, nombre);
		putValue(Action.SHORT_DESCRIPTION, _setDescripcion(nombre));
	}
	
	private String _setDescripcion(String caracter) {
		String str = "";
		switch (caracter) {
		case "Arrancar":
		case "Reanudar":
			str = "Iniciar la ejecución";
			break;
		case "Pausar":
			str = "pausa la ejecución";
			break;
		case "Resetear":
			str = "Reiniciar el juego";
			break;
		}
		return str;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cadena = (String) getValue(Action.NAME); {
			switch (cadena) {
			case "Arrancar":
			case "Reanudar":
				controlador.ejecutar(true);
				break;
			case "Pausar":
				controlador.ejecutar(false);
				break;
			case "Resetear":
				controlador.resetSliders();
				controlador.reset();
				break;
			}
		}
	}
}
