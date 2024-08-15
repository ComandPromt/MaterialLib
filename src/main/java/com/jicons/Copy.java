package com.jicons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Copy extends ImageIcon {

	private Color fondo;

	private Color fondoPagina;

	private Color linea;

	public void setFondo(Color fondo) {

		this.fondo = fondo;

	}

	public void setFondoPagina(Color fondoPagina) {

		this.fondoPagina = fondoPagina;

	}

	public void setLinea(Color linea) {

		this.linea = linea;

	}

	public Copy() {

		fondo = Color.LIGHT_GRAY;

		fondoPagina = Color.WHITE;

		linea = Color.BLACK;

	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {

		int width = c.getWidth();

		int height = c.getHeight();

		g.setColor(c.getBackground());

		g.fillRect(0, 0, width, height);

		g.setColor(fondo);

		g.fillRect(0, 0, Math.round(width * 0.625f), Math.round(height * 0.625f));

		g.setColor(fondoPagina);

		g.fillRect(Math.round(width * 0.375f), Math.round(height * 0.375f), Math.round(width * 0.625f),
				Math.round(height * 0.625f));

		g.setColor(linea);

		g.drawLine(Math.round(width * 0.5f), Math.round(height * 0.5f), Math.round(width * 0.875f),
				Math.round(height * 0.5f));

		g.drawLine(Math.round(width * 0.5f), Math.round(height * 0.625f), Math.round(width * 0.875f),
				Math.round(height * 0.625f));

		g.drawLine(Math.round(width * 0.5f), Math.round(height * 0.75f), Math.round(width * 0.875f),
				Math.round(height * 0.75f));

		g.drawLine(Math.round(width * 0.5f), Math.round(height * 0.875f), Math.round(width * 0.875f),
				Math.round(height * 0.875f));

	}

}