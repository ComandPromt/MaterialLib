package com.materialfilechooser.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JButton;

@SuppressWarnings("serial")

public class CarpetaPadre extends JButton {

	private Color color;

	private Color background;

	private Color inner;

	public CarpetaPadre(Color color, Color background, Color inner) {

		if (color == null) {

			color = Color.BLACK;

		}

		if (background == null) {

			background = Color.WHITE;

		}

		if (inner == null) {

			inner = Color.WHITE;

		}

		this.color = color;

		this.background = background;

		this.inner = inner;

	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		g.setColor(background);

		g.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(color);

		int calculo = Math.round((int) (getHeight() * 0.1666));

		int mitadX = getWidth() / 2;

		int[] xPoints = { 0, 0, getWidth(), getWidth(), mitadX, Math.round(getWidth() * 0.3333f) };

		int[] yPoints = { 0, getHeight(), getHeight(), calculo, calculo, 0 };

		int numPoints = xPoints.length;

		Polygon polygon = new Polygon(xPoints, yPoints, numPoints);

		g2.fillPolygon(polygon);

		int anchura = getWidth();

		int altura = getHeight();

		int x = 0;

		int y = 0;

		int separacion = anchura / (4);

		Point punto1 = new Point(x + anchura / 2, y);

		Point punto2 = new Point(x + anchura, y + altura / 2);

		Point punto3 = new Point(x + anchura - separacion, y + altura / 2);

		Point punto4 = new Point(x + anchura - separacion, y + altura);

		Point punto5 = new Point(x + separacion, y + altura);

		Point punto6 = new Point(x + separacion, y + altura / 2);

		Point punto7 = new Point(x, y + altura / 2);

		int[] puntosX = new int[] { punto1.x, punto2.x, punto3.x, punto4.x, punto5.x, punto6.x, punto7.x };

		int[] puntosY = new int[] { punto1.y, punto2.y, punto3.y, punto4.y, punto5.y, punto6.y, punto7.y };

		g2.scale(0.5, 0.5);

		g2.translate(mitadX, getHeight() / 1.5);

		g2.setColor(inner);

		g2.fillPolygon(puntosX, puntosY, 7);

	}

}
