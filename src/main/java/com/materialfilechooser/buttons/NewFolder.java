package com.materialfilechooser.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class NewFolder extends JButton {

	private Color color;

	private Color background;

	private Color inner;

	public NewFolder(Color color, Color background, Color inner) {

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

		Polygon polygon = new Polygon(xPoints, yPoints, xPoints.length);

		g2.fillPolygon(polygon);

		int separacionX = getWidth() / 3;

		int separacionY = getHeight() / 3;

		Point punto1 = new Point(separacionX, 0);

		Point punto2 = new Point(getWidth() - separacionX, 0);

		Point punto3 = new Point(getWidth() - separacionX, separacionY);

		Point punto4 = new Point(getWidth(), separacionY);

		Point punto5 = new Point(getWidth(), getHeight() - separacionY);

		Point punto6 = new Point(getWidth() - separacionX, getHeight() - separacionY);

		Point punto7 = new Point(getWidth() - separacionX, getHeight());

		Point punto8 = new Point(separacionX, getHeight());

		Point punto9 = new Point(separacionX, getHeight() - separacionY);

		Point punto10 = new Point(0, getHeight() - separacionY);

		Point punto11 = new Point(0, separacionY);

		Point punto12 = new Point(separacionX, separacionY);

		int[] puntosX = { punto1.x, punto2.x, punto3.x, punto4.x, punto5.x, punto6.x, punto7.x, punto8.x, punto9.x,
				punto10.x, punto11.x, punto12.x };

		int[] puntosY = { punto1.y, punto2.y, punto3.y, punto4.y, punto5.y, punto6.y, punto7.y, punto8.y, punto9.y,
				punto10.y, punto11.y, punto12.y };

		g2.scale(0.5, 0.5);

		g2.translate(mitadX, getHeight() / 1.5);

		g2.setColor(inner);

		g2.fillPolygon(puntosX, puntosY, 12);

	}

}
