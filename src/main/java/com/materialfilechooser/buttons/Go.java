package com.materialfilechooser.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JButton;

public class Go extends JButton {

	private Color color;

	private Color background;

	public Go(Color color, Color background) {

		if (color == null) {

			color = Color.BLACK;

		}

		if (background == null) {

			background = Color.WHITE;

		}

		this.color = color;

		this.background = background;

	}

	@Override

	public void paint(Graphics g) {

		g.setColor(background);

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(color);

		int x = 0;

		int y = 0;

		int anchura = getWidth();

		int altura = getHeight();

		int separacion = altura / (4);

		Point punto1 = new Point(x + anchura / 2, y);

		Point punto2 = new Point(x + anchura, y + altura / 2);

		Point punto3 = new Point(x + anchura / 2, y + altura);

		Point punto4 = new Point(x + anchura / 2, y + altura - separacion);

		Point punto5 = new Point(x, y + altura - separacion);

		Point punto6 = new Point(x, y + separacion);

		Point punto7 = new Point(x + anchura / 2, y + separacion);

		int[] puntosX = new int[] { punto1.x, punto2.x, punto3.x, punto4.x, punto5.x, punto6.x, punto7.x };

		int[] puntosY = new int[] { punto1.y, punto2.y, punto3.y, punto4.y, punto5.y, punto6.y, punto7.y };

		g.fillPolygon(puntosX, puntosY, 7);

	}

}
