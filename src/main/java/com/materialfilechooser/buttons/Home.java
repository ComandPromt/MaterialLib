package com.materialfilechooser.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Home extends JButton {

	private Color color;

	private Color background;

	public Home(Color color, Color background) {

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

		int mitadX = getWidth() / 2;

		int mitadY = getHeight() / 2;

		int calculo = (int) Math.round((getWidth() * 0.125));

		int calculoY = (int) Math.round((getHeight() * 0.125));

		int[] xPoints = { mitadX, 0, calculo, calculo, mitadX - calculo, mitadX - calculo, mitadX + calculo,
				mitadX + calculo, (mitadX + calculo) + (mitadX / 2), getWidth() - (calculo + 1), getWidth() };

		int[] yPoints = { 0, mitadY, mitadY, getHeight(), getHeight(), mitadY + calculoY + (calculoY / 2),
				mitadY + calculoY + (calculoY / 2), getHeight(), getHeight(), mitadY, mitadY };

		Polygon polygon = new Polygon(xPoints, yPoints, 11);

		g.fillPolygon(polygon);

	}

}
