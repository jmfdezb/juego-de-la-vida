package juegovida.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class Boton extends JButton {
	private static final long serialVersionUID = 1L;
	private final Color [] colorBoton = { new Color(72,189,240), new Color(49,180,241) };
	private final Color colorBorde = Color.DARK_GRAY;
	private String etiqueta;
	
    private BufferedImage imagenBoton;
	
	public Boton(String etiqueta) {
		this.etiqueta = etiqueta;
		setMinimumSize(new Dimension(100,25));
		setHideActionText(true);
		this.imagenBoton = crearImagen();
	}
	
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
		this.repaint();
	}
	
	private void aplicarEfectos(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	private BufferedImage crearImagen() {
        GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage imagen = gfxConf.createCompatibleImage(100, 85, Transparency.TRANSLUCENT);
        Graphics2D g2 = (Graphics2D) imagen.getGraphics();
        aplicarEfectos(g2);
        
        
        GeneralPath boton = new GeneralPath();
        boton.moveTo(0, 15);
        boton.curveTo(0, 15, 0, 0, 15, 0);
        boton.lineTo(80, 0);
        boton.curveTo(80, 0, 95, 0, 95, 15);
        boton.curveTo(95, 15, 95, 30, 80, 30);
        boton.lineTo(15, 30);
        boton.curveTo(15, 30, 0, 30, 0, 15);
        boton.closePath();
       	colorear(g2, boton, colorBoton[0], colorBorde);
        g2.dispose();
        return imagen;
	}
	
	private void colorear(Graphics2D g2, GeneralPath boton, Color principal, Color borde) {
			g2.setColor(principal);
			g2.fill(boton);
			g2.setColor(borde);
			g2.draw(boton);
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
        Graphics2D g2 = (Graphics2D) g.create();
		aplicarEfectos(g2);
        g2.drawImage(imagenBoton, 0, 1, this);
        if (this.isEnabled()) { g2.setColor(Color.white); } else { g2.setColor(Color.LIGHT_GRAY); }
        g2.setFont(new java.awt.Font("Aharoni", 1, 14));
        int x = 0;
        if (etiqueta == "Pausar") { x = 20; } else { x = 13; }
        g2.drawString(this.etiqueta, x, 20);
    }

}

