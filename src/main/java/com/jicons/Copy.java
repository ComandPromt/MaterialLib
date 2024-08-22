package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Copy extends ImageIcon {

	private Color fondo;

	private Color fondoPagina;

	private Color linea;

	private int grosor;

	private Component c;

	private void repintar() {

		try {

			c.repaint();

		}

		catch (Exception e) {

		}

	}

	public void setGrosor(int grosor) {

		this.grosor = grosor;

		repintar();

	}

	public void setFondo(Color fondo) {

		this.fondo = fondo;

		repintar();

	}

	public void setFondoPagina(Color fondoPagina) {

		this.fondoPagina = fondoPagina;

		repintar();

	}

	public void setLinea(Color linea) {

		this.linea = linea;

		repintar();

	}

	public Copy() {

		fondo = Color.LIGHT_GRAY;

		fondoPagina = Color.WHITE;

		linea = Color.BLACK;

	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {

		this.c = c;

		int width = c.getWidth();

		int height = c.getHeight();

		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(grosor));

		g2d.setColor(c.getBackground());

		g2d.fillRect(0, 0, width, height);

		g2d.setColor(fondo);

		g2d.fillRect(0, 0, Math.round(width * 0.625f), Math.round(height * 0.625f));

		g2d.setColor(fondoPagina);

		g2d.fillRect(Math.round(width * 0.375f), Math.round(height * 0.375f), Math.round(width * 0.625f),
				Math.round(height * 0.625f));

		g2d.setColor(linea);

		g2d.drawRect(Math.round(width * 0.375f), Math.round(height * 0.375f), Math.round(width * 0.625f) - 2,
				Math.round(height * 0.625f) - 2);

		g2d.drawLine(Math.round(width * 0.5f), Math.round(height * 0.5f), Math.round(width * 0.875f),
				Math.round(height * 0.5f));

		g2d.drawLine(Math.round(width * 0.5f), Math.round(height * 0.625f), Math.round(width * 0.875f),
				Math.round(height * 0.625f));

		g2d.drawLine(Math.round(width * 0.5f), Math.round(height * 0.75f), Math.round(width * 0.875f),
				Math.round(height * 0.75f));

		g2d.drawLine(Math.round(width * 0.5f), Math.round(height * 0.875f), Math.round(width * 0.875f),
				Math.round(height * 0.875f));

	}

}