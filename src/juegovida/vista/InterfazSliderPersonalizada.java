package juegovida.vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicSliderUI;

//https://stackoverflow.com/questions/12293982/change-the-jslider-look-and-feel/12297384
public class InterfazSliderPersonalizada extends BasicSliderUI {

	 private BasicStroke stroke = new BasicStroke(1f, BasicStroke.CAP_ROUND, 
	            BasicStroke.JOIN_ROUND, 0f, new float[]{1f, 2f}, 0f);
	 private final Color [] colorKnob = { new Color(72,189,240), new Color(49,180,241) };
	 private boolean habilitado;
	 
	public InterfazSliderPersonalizada(JSlider b, boolean habilitado) {
		super(b);
		this.habilitado = habilitado;
	}
	
	   @Override
	    public void paint(Graphics g, JComponent c) {
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        super.paint(g, c);
	    }

	    @Override
	    protected Dimension getThumbSize() {
	        return new Dimension(12, 16);
	    }

	    @Override
	    public void paintTrack(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;
	        Stroke old = g2d.getStroke();
	        g2d.setStroke(stroke);
	        g2d.setPaint(Color.WHITE);
	        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
	            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2, 
	                    trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
	        } else {
	            g2d.drawLine(trackRect.x + trackRect.width / 2, trackRect.y, 
	                    trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height);
	        }
	        g2d.setStroke(old);
	    }

	    @Override
	    public void paintThumb(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;
	        int x1 = thumbRect.x + 2;
	        int x2 = thumbRect.x + thumbRect.width - 2;
	        int width = thumbRect.width - 4;
	        int topY = thumbRect.y + thumbRect.height / 2 - thumbRect.width / 3;
	        GeneralPath shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
	        shape.moveTo(x1, topY);
	        shape.lineTo(x2, topY);
	        shape.lineTo(x2, topY+width);
	        shape.lineTo(x1, topY+width);
	        shape.closePath();
	        if (habilitado) { g2d.setPaint(colorKnob[1]); } else { g2d.setPaint(Color.darkGray); }  
	        g2d.fill(shape);
	        
	        Stroke old = g2d.getStroke();
	        g2d.setStroke(new BasicStroke(2f));
	        if (habilitado) { g2d.setPaint(colorKnob[0]); } else { g2d.setPaint(Color.gray); }  
	        g2d.draw(shape);
	        g2d.setStroke(old);
	    }

}
