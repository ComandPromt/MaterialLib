package com.jicons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.ImageIcon;

import mthos.JMthos;

@SuppressWarnings("serial")
public class ClassicFolder extends ImageIcon {

	private Color linea;

	private int grosor;

	private Component c;

	private Color fondo;

	private int restarDerecha;

	public void setRestarDerecha(int restarDerecha) {

		this.restarDerecha = restarDerecha;

		repintar();

	}

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

	public void setLinea(Color linea) {

		this.linea = linea;

		repintar();

	}

	public ClassicFolder(Color fondo) {

		this();

		this.fondo = fondo;

	}

	public ClassicFolder() {

		linea = Color.BLACK;

	}

	public void setFondo(Color fondo) {

		this.fondo = fondo;

	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {

		this.c = c;

		int width = c.getWidth();

		int height = c.getHeight();

		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(grosor));

		String code = "g2d.drawLine(1, 1, Math.round(width * 0.2f), 1);\r\n"
				+ "g2d.drawLine(Math.round(width * 0.2f), 1, Math.round(width * 0.3f), Math.round(height * 0.1f));\r\n"
				+ "g2d.drawLine(Math.round(width * 0.3f), Math.round(height * 0.1f), width - " + restarDerecha
				+ ", Math.round(height * 0.1f));\r\n" + "g2d.drawLine(width - " + restarDerecha
				+ ", Math.round(height * 0.1f), width - " + restarDerecha + ", height - 1);\r\n"
				+ "g2d.drawLine(width - " + restarDerecha + ", height - 1, 1, height - 1);\r\n"
				+ "g2d.drawLine(1, height - 1, 1, 1);";

		Polygon polygon = JMthos.getPolygonFromCode(code, width, height);

		if (fondo == null) {

			fondo = c.getBackground();

		}

		g2d.setColor(fondo);

		g2d.fillPolygon(polygon);

		g2d.setColor(linea);

		g2d.drawPolygon(polygon);

	}

}