package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Ojo extends ImageIcon {

	private Color color;

	private float grosor;

	private int size;

	private boolean tachado;

	public void setSizeIcon(int size) {

		try {

			this.size = size;

		}

		catch (Exception e) {

		}

	}

	public Ojo(Color color) {

		this(30, color);

	}

	public Ojo() {

		this(30, Color.BLACK);

	}

	public Ojo(int size, Color color) {

		this.size = size;

		grosor = 2f;

		this.color = color;

		setImage(createImage(size));

	}

	private BufferedImage createImage(int size) {

		BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = image.createGraphics();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		g2d.setColor(Color.WHITE);

		g2d.fillRect(0, 0, size, size);

		g2d.setStroke(new BasicStroke(grosor));

		g2d.setColor(color);

		int ancho = (size * 550) / 392 - 3;

		int alto = ((size * 710) / 211) - 4;

		int equis = (size * (-80)) / 392;

		int x = -5;

		int y = 1;

		g2d.drawArc(x, y, ancho, alto, 45, 90);

		g2d.drawArc(x, y + ((alto * (-500)) / 710), ancho, --alto, -45, -90);

		g2d.drawOval(x + Math.round(size / 2.26087f), y + Math.round(size * 0.1f), Math.round(size / 2f),
				Math.round(size * 0.75f));

		equis = (size * 30) / 260;

		if (tachado) {

			g2d.drawLine(x + (equis * 2), y + equis, (x + (size * 1)) + equis, y + (size - (equis)));

		}

		g2d.dispose();

		return image;

	}

	public void setGrosor(float grosor) {

		this.grosor = grosor;

		setImage(createImage(size));

	}

	public void setColor(Color color) {

		this.color = color;

		setImage(createImage(size));

	}

	public void setTachado(boolean tachado) {

		this.tachado = tachado;

		setImage(createImage(size));

	}

	public boolean isTachado() {

		return tachado;

	}

}
