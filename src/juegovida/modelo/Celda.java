package juegovida.modelo;

import java.util.Random;

public class Celda {
	private boolean salud = false;
	private static final Random ALEATORIO = new Random();
	private int[] posicion = new int[2];
	private int vecinos = 0;
	
	public Celda(int x, int y, int porcentajeVivas) {
		posicion[0] = x;
		posicion[1] = y;
		this.salud = ALEATORIO.nextInt(100) < porcentajeVivas ? true : false;
	}
	
	public boolean isViva() { 
		return salud;
	}
	
	public void setVecinos(int vecinos) {
		this.vecinos = vecinos;
	}
	
	public int getVecinos() {
		return this.vecinos;
	}
	
	public void setSalud(boolean salud) {
		this.salud = salud;
	}
	
	public void matar() {
		this.salud = false;
	}
	
	public void revivir() {
		this.salud = true;
	}
	
	public int[] getPosicion() {
		return this.posicion;
	}
	
}
