package juegovida.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class MenuInferior extends JPanel{
	private static final long serialVersionUID = 1L;
	private static final Color[] COLORES = { new Color(45,45,45), new Color(60,60,60) };
	private static Dimension tamano;
	private static final String[] TEXTO_ETIQUETAS = { "Generación: ", "Vivas: ", "Muertas: ", "", "", "", "Núm células", "Porcentaje vivas" };
	private static final Boton[] BOTONES = new Boton[2];
 	private static final JLabel[] ETIQUETAS = new JLabel[8];
	private static final JSlider[] SLIDERS = new JSlider[2];
	
	public MenuInferior(Dimension tamano) {
		MenuInferior.tamano = tamano;
		this.setPreferredSize(tamano);
		for (int i = 0; i < ETIQUETAS.length; i++) {
			ETIQUETAS[i] = new JLabel(TEXTO_ETIQUETAS[i]);
			ETIQUETAS[i].setForeground(new Color(72,189,240));
			ETIQUETAS[i].setHorizontalAlignment(JLabel.RIGHT);
			if ((i > 2) && ( i < 6 )) { ETIQUETAS[i].setMinimumSize(new Dimension(40,25)); }
		}
		
		BOTONES[0] = new Boton("Arrancar");
		BOTONES[0].setName("BotonStart");

		BOTONES[1] = new Boton("Resetear");
		BOTONES[1].setName("BotonReset");
		
		SLIDERS[0] = new JSlider(0,100,20);
		SLIDERS[0].setMinorTickSpacing(100/6);
		SLIDERS[0].setName("SlideNumeroCelulas");
		SLIDERS[0].setToolTipText("Número de células");
		SLIDERS[0].setMinimumSize(new Dimension(100,25));
		
		SLIDERS[1] = new JSlider(10,90,50);
		SLIDERS[1].setMinorTickSpacing(10);
		SLIDERS[1].setName("SlidePorcentajeVivas");
		SLIDERS[1].setToolTipText("Porcentaje células vivas");
		SLIDERS[1].setMinimumSize(new Dimension(130,25));
	    
		for (int i = 0; i < 2; i++) {
			BOTONES[i].setContentAreaFilled(false);
			BOTONES[i].setFocusPainted(false);
			BOTONES[i].setBorderPainted(false);
			SLIDERS[i].setSnapToTicks(true);
			
			SLIDERS[i].setOpaque(false);
			SLIDERS[i].setUI(new InterfazSliderPersonalizada(SLIDERS[i], true));
		}
	    
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
	    c.weightx = 0.1;
	    c.weighty = 0.1;
	    c.anchor = GridBagConstraints.EAST; 
	    c.fill = GridBagConstraints.VERTICAL;
	    
	    
	    int contador = 0;
		for (int j = 0; j < 2; j++) {
			if (j == 0) { c.anchor = GridBagConstraints.EAST; } else {  c.anchor = GridBagConstraints.WEST; }
			for (int i = 0; i < 3; i++) {
				if (i == 0 ) { c.insets = new Insets(15,0,0,0); } 
				else if (i == 1 ) {c.insets = new Insets(0,0,0,0); }
				else { c.insets = new Insets(0,0,5,0); }
				c.gridx = j;
				c.gridy = i;
				this.add(ETIQUETAS[contador++],c);
			}
		}
		c.insets = new Insets(20,0,0,0);
		c.gridheight = 3;
		c.gridy = 0;
	    c.gridx++;
	    
		this.add(BOTONES[0],c);
		c.gridx++;
		this.add(BOTONES[1],c);
//		c.gridy++;
		c.gridx++;
		c.gridy = 0;
		c.gridheight = 1;
//		c.weightx = 0;
		c.insets = new Insets(20,0,0,0);
		this.add(ETIQUETAS[6],c);
		c.gridy++;
		c.gridheight = 2;
		c.insets = new Insets(0,-6,0,0);
		this.add(SLIDERS[0],c);
		c.gridx++;
		c.gridy = 0;
		c.gridheight = 1;
		c.insets = new Insets(20,0,-2,2);
		this.add(ETIQUETAS[7],c);
		c.gridy++;
		c.gridheight = 2;
		c.insets = new Insets(0,-6,0,2);
		this.add(SLIDERS[1],c);
		this.setVisible(true);
	}
	
	public void setGeneracion(int generacion) {
		ETIQUETAS[3].setText(String.format("%d",generacion));
	}
	
	public void setVivas(int vivas) {
		ETIQUETAS[4].setText(String.format("%d",vivas));
	}
	
	public void setMuertas(int muertas) {
		ETIQUETAS[5].setText(String.format("%d",muertas));
	}
	
	public void resetSliders() {
		SLIDERS[0].setValue(100/6);
		SLIDERS[1].setValue(50);
	}
	
	public void setEjecutando(boolean ejecutando) {
		if (ejecutando) {
			BOTONES[0].setEtiqueta("Parar");
			BOTONES[1].setEnabled(false);
			deshabilitarSliders();
		} else {
			BOTONES[0].setEtiqueta("Empezar");
			BOTONES[1].setEnabled(true);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Point2D inicio = new Point2D.Float(0, 0);
		final Point2D fin = new Point2D.Float(tamano.width, tamano.height);
		final float[] gradiente = { 0.0f, 1.0f };
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(new LinearGradientPaint(inicio, fin, gradiente, COLORES));
		g2.fillRect(0, 0, tamano.width, tamano.height);
		
		g.setColor(new Color(72,189,240));
		g.fillRect(0, 0, 6, tamano.height);
		g.fillRect(tamano.width - 6, 0, 6, tamano.height);
		for (int i = 0; i < tamano.width; i+=12) {
			g.fillRect(i, 0, 6, 10);
			g.fillRect(i+6, 0, 6, 5);
		}
		g2.dispose();
	}

	public void habilitarSliders() {
		for (int i = 0; i < 2; i++) {
			SLIDERS[i].setUI(new InterfazSliderPersonalizada(SLIDERS[i], true));
			SLIDERS[i].setEnabled(true);
		}
	}
	
	private void deshabilitarSliders() {
		for (int i = 0; i < 2; i++) {
			SLIDERS[i].setUI(new InterfazSliderPersonalizada(SLIDERS[i], false));
			SLIDERS[i].setEnabled(false);
		}
	}
		
}
