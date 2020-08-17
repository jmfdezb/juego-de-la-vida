package juegovida.modelo;

import java.util.ArrayList;

public class Modelo {
	private static Celda[][] universo;
	private static int vivas = 0, muertas = 0, generacion = 1;
	
	public Modelo(int tamano) {
		resetUniverso(tamano, 50);
	}
	
	private static void resetUniverso(int tamano, int porcentajeVivas) {
		universo = new Celda[tamano][tamano];
		for (int i = 0; i < universo.length; i++) {
			for (int j = 0; j < universo[i].length; j++) {
				universo[i][j] = new Celda(i,j, porcentajeVivas);
				if (universo[i][j].isViva()) { vivas++; } else { muertas++; }
			}
		}
	}
	
	private void getVecinos(Celda celda) {
		int[] posicion = celda.getPosicion();
		int vecinos = 0;
		int x, y;
		for (int i = posicion[0]-1; i <= posicion[0]+1; i++) {
			for (int j = posicion[1]-1; j <= posicion[1]+1; j++) {
				if (i == -1 ) { x = universo.length-1; }
				else if (i == universo.length) { x = 0; }
				else { x = i; }
				
				if (j == -1 ) { y = universo.length-1; }
				else if (j == universo.length) { y = 0; }
				else { y = j; }
				
				if ( (x == posicion[0]) && (y == posicion[1]) ) { continue; }
				if (universo[x][y].isViva()) { vecinos++; }
			}
		}
		celda.setVecinos(vecinos);
	}
	
	public void iteracion() {
		Modelo.vivas = 0;
		Modelo.muertas = 0;
		int cambios = 0;	
		ArrayList<Celda> celdas = new ArrayList<Celda>();
		for (int j = 0; j < universo.length; j++) {
			for (int i = 0; i < universo[0].length; i++) {
				this.getVecinos(universo[i][j]);
				celdas.add(universo[i][j]);
			}
		}

		for (Celda c : celdas) {
			int vecinos = c.getVecinos();
			if (c.isViva()) {
				if (vecinos < 2) { c.matar(); Modelo.muertas++; cambios++;}
				else if (vecinos == 2 || vecinos == 3 ) { Modelo.vivas++; continue; } 
				else { c.matar(); muertas++; cambios++;} 
			} else {
				if (vecinos == 3) { c.revivir(); Modelo.vivas++; cambios++;} else { Modelo.muertas++; }
			}
		}
		if ( cambios > 0 ) { Modelo.generacion++; }
		
	}
	
	public static void reset(int numeroCelulas, int porcentajeVivas) {
		Modelo.vivas = 0;
		Modelo.muertas = 0;
		Modelo.generacion = 1;
		Modelo.resetUniverso(numeroCelulas, porcentajeVivas);
	}
	
	public static int getVivas() {
		return vivas;
	}

	public static int getGeneracion() {
		return generacion;
	}

	public static int getMuertas() {
		return muertas;
	}

	public static Celda[][] getTablero() {
		return universo;
	}
	
	public void setUniverso(Celda[][] universo) {
		Modelo.universo = universo;
	}
}
