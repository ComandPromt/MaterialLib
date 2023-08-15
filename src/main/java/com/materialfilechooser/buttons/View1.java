package com.materialfilechooser.buttons;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

@SuppressWarnings("serial")

public class View1 extends JButton {

	private Color color;

	private Color background;

	public View1(Color color, Color background) {

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

		int ancho = (int) Math.round(getWidth() * 0.40);

		int alto = (int) Math.round(getHeight() * 0.40);

		g.setColor(background);

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(color);

		g.fillRect(0, 0, ancho, alto);

		g.fillRect(ancho + (ancho / 2), 0, ancho, alto);

		g.fillRect(0, alto + (alto / 2), ancho, alto);

		g.fillRect(ancho + (ancho / 2), alto + (alto / 2), ancho, alto);

	}

}
