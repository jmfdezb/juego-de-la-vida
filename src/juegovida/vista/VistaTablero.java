package juegovida.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

import juegovida.modelo.Celda;

public class VistaTablero extends JPanel{
	private static final long serialVersionUID = 1L;
	private static Celda arregloCeldas[][];
	private static Dimension tamano;

	public VistaTablero(Dimension tamano) {
		VistaTablero.tamano = tamano;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = 0.1;
	    c.weighty = 0.1;
	    c.fill = GridBagConstraints.BOTH;
		this.setOpaque(true);
		this.setBackground(Color.green);
		this.setPreferredSize(tamano);
	}
	
	public void actualizarTablero(Celda[][] matriz) {
		arregloCeldas = matriz;
		repaint();
	}
	
	private void pintarCelda(Graphics2D g2, int x, int y, boolean viva) {
		Color vida = (viva) ? new Color( 0, 0, 0 ) : new Color( 255, 255, 255 );
		Rectangle celda = new Rectangle(x, y, tamano.width/arregloCeldas.length, tamano.height/arregloCeldas[0].length);
		g2.setColor(Color.DARK_GRAY);
		g2.draw(celda);
		g2.setColor(vida);
		g2.fill(celda);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x = 0, y = 0;
		Graphics2D g2 = (Graphics2D) g.create();
		for (int i = 0; i < arregloCeldas.length; i++) {
			for (int j = 0; j < arregloCeldas[0].length; j++) {
				pintarCelda(g2, x, y, arregloCeldas[i][j].isViva());
				x+=tamano.width/arregloCeldas.length;
			}
			x=0;
			y+=tamano.height/arregloCeldas[0].length;
		}
	}
}
