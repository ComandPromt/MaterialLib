package com.jicons;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")

public class Eye extends ImageIcon {

	private boolean right;

	private boolean tachado;

	private int ancho;

	private int alto;

	private int equis;

	private int coordenadaX;

	private int coordenadaY;

	private boolean coordenadas;

	private float grosor;

	private Component c;

	public void setGrosor(float grosor) {

		this.grosor = grosor;

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

	public boolean isCoordenadas() {

		return coordenadas;

	}

	public void setCoordenadas(boolean coordenadas) {

		this.coordenadas = coordenadas;

	}

	public Eye() {

		this(false);

	}

	public Eye(boolean cero) {

		grosor = 1f;

		coordenadas = true;

	}

	public int getCoordenadaX() {

		return coordenadaX;

	}

	public void setCoordenadaX(int coordenadaX) {

		this.coordenadaX = coordenadaX;

	}

	public int getCoordenadaY() {

		return coordenadaY;

	}

	public void setCoordenadaY(int coordenadaY) {

		this.coordenadaY = coordenadaY;

	}

	public int getWidth() {

		return ancho;

	}

	public void setWidth(int ancho) {

		this.ancho = ancho;

	}

	public int getHeight() {

		return alto;

	}

	public void setHeight(int alto) {

		this.alto = alto;

	}

	public void setTachado(boolean tachado) {

		this.tachado = tachado;

	}

	public void setRight(boolean right) {

		this.right = right;

	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {

		this.c = c;

		if (ancho == 0 || alto == 0) {

			ancho = c.getWidth();

			alto = c.getHeight();

		}

		drawContent(c, g, x, y, ancho, alto);

	}

	private void drawContent(Component c, Graphics g, int x, int y, int anchura, int altura) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(c.getBackground());

		g2d.fillRect(0, 0, getWidth(), getHeight());

		g2d.setStroke(new BasicStroke(grosor));

		if (coordenadas) {

			x = coordenadaX;

			x -= Math.round(c.getWidth() * 0.2f);

			y = coordenadaY;

			y += 1;

		}

		g2d.setColor(c.getForeground());

		ancho = (anchura * 550) / 392;

		alto = ((altura * 710) / 211) - 4;

		equis = (anchura * (-80)) / 392;

		g2d.drawArc(x, y, ancho, alto, 45, 90);

		g2d.drawArc(x, y + ((alto * (-500)) / 710), ancho, --alto, -45, -90);

		g2d.drawOval(x + Math.round(anchura / 2.26087f), y + Math.round(altura * 0.1f), Math.round(anchura / 2f),
				Math.round(altura * 0.75f));

		equis = (anchura * 30) / 260;

		if (tachado) {

			if (right) {

				g2d.drawLine(x, y + (altura - (equis)), anchura - (equis), equis);

			}

			else {

				g2d.drawLine(x + (equis * 2), y + equis, (x + (anchura * 1)) + equis, y + (altura - (equis)));

			}

		}

	}

}
