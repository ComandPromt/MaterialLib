package com.materialfilechooser.buttons;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class View2 extends JButton {

	private Color color;

	private Color background;

	public View2(Color color, Color background) {

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

		int altura = (int) Math.round(getHeight() * 0.20);

		g.fillRect(0, 0, getWidth(), altura);

		g.fillRect(0, altura * 2, getWidth(), altura);

		g.fillRect(0, altura * 4, getWidth(), altura);

	}

}
