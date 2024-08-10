package com.panels.image;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelCircleImage extends JPanel {

	private Icon icono;

	ImageIcon imagenIcon = (ImageIcon) this.icono;

	private Image image;

	private Image image_default = this.image;

	private Color colorBorde = new Color(0, 112, 192);

	private Color colorFondo = new Color(255, 255, 255);

	public PanelCircleImage() {
		setPreferredSize(new Dimension(150, 150));
		setSize(new Dimension(150, 150));
		setOpaque(false);
	}

	public void setImagen(Icon icon) {
		if (icon != null) {
			this.icono = icon;
			this.imagenIcon = (ImageIcon) this.icono;
			this.image = this.imagenIcon.getImage();
		} else {
			this.image = this.image_default;
		}
		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(this.colorFondo);
		g2.fill(new Ellipse2D.Double(3.0D, 3.0D, (w - 6), (h - 6)));
		g2.setClip(new Ellipse2D.Float(3.0F, 3.0F, (w - 6), (h - 6)));
		g2.drawImage(this.image, 3, 3, w - 6, h - 6, this);
		g2.dispose();
	}

	protected void paintBorder(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(5.0F));
		g2.setColor(this.colorBorde);
		g2.drawOval(3, 3, w - 6, h - 6);
		g2.dispose();
	}

	public Color getColorBorde() {
		return this.colorBorde;
	}

	public void setColorBorde(Color colorBorde) {
		this.colorBorde = colorBorde;
	}

	public Color getColorFondo() {
		return this.colorFondo;
	}

	public void setColorFondo(Color colorFondo) {
		this.colorFondo = colorFondo;
	}
}
