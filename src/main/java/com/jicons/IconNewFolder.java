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
public class IconNewFolder extends ImageIcon {

	private Component c;

	private int grosor;

	private Color linea;

	private Color fondo;

	public void setFondo(Color fondo) {

		this.fondo = fondo;

		JMthos.repintar(c);

	}

	public IconNewFolder() {

		linea = Color.BLACK;

	}

	public void setGrosor(int grosor) {

		this.grosor = grosor;

		JMthos.repintar(c);

	}

	public void setLinea(Color linea) {

		this.linea = linea;

		JMthos.repintar(c);

	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {

		this.c = c;

		int width = c.getWidth();

		int height = c.getHeight();

		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(grosor));

		String code = "g2d.drawLine(1, 1, Math.round(width * 0.2f), 1);\r\n" + "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.2f), 1, Math.round(width * 0.3f), Math.round(height * 0.1f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.3f), Math.round(height * 0.1f), width - 1, Math.round(height * 0.1f));\r\n"
				+ "\r\n" + "		g2d.drawLine(width - 1, Math.round(height * 0.1f), width - 1, height - 1);\r\n"
				+ "\r\n" + "		g2d.drawLine(width - 1, height - 1, 1, height - 1);\r\n" + "\r\n"
				+ "		g2d.drawLine(1, height - 1, 1, 1);";

		Polygon polygon = JMthos.getPolygonFromCode(code, width, height);

		g2d.setColor(c.getBackground());

		g2d.fillPolygon(polygon);

		g2d.setColor(linea);

		g2d.drawPolygon(polygon);

		code = "		g2d.drawLine(Math.round(width * 0.4166f), Math.round(height * 0.333f), Math.round(width * 0.5833f),Math.round(height * 0.333f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.5833f), Math.round(height * 0.333f), Math.round(width * 0.5833f),Math.round(height * 0.5f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.5833f), Math.round(height * 0.5f), Math.round(width * 0.75f),Math.round(height * 0.5f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.75f), Math.round(height * 0.5f), Math.round(width * 0.75f),Math.round(height * 0.666f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.75f), Math.round(height * 0.666f), Math.round(width * 0.5833f),Math.round(height * 0.666f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.5833f), Math.round(height * 0.666f), Math.round(width * 0.5833f),Math.round(height * 0.833f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.5833f), Math.round(height * 0.833f), Math.round(width * 0.4166f),Math.round(height * 0.833f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.4166f), Math.round(height * 0.833f), Math.round(width * 0.4166f),Math.round(height * 0.666f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.4166f), Math.round(height * 0.666f), Math.round(width * 0.25f),Math.round(height * 0.666f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.25f), Math.round(height * 0.666f), Math.round(width * 0.25f),Math.round(height * 0.5f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.25f), Math.round(height * 0.5f), Math.round(width * 0.4166f),Math.round(height * 0.5f));\r\n"
				+ "\r\n"
				+ "		g2d.drawLine(Math.round(width * 0.4166f), Math.round(height * 0.5f), Math.round(width * 0.4166f),Math.round(height * 0.333f));";

		polygon = JMthos.getPolygonFromCode(code, width, height);

		g2d.setColor(fondo);

		g2d.fillPolygon(polygon);

		g2d.setColor(linea);

		g2d.drawPolygon(polygon);

	}

}
