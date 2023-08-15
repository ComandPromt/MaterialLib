package com.materialfilechooser.icons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JLabel;

public class Archivo extends JLabel {

	private Color color;

	private Color background;

	public Archivo(Color color, Color background) {

		if (color == null) {

			color = Color.decode("#8b8b8b");

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

		int calculoX = Math.round(getWidth() * 0.6666f);

		int calculoY = Math.round(getHeight() * 0.6666f);

		int[] xPoints = { 0, 0, getWidth(), getWidth(), getWidth(), calculoX };

		int[] yPoints = { 0, getHeight(), getHeight(), calculoY / 2, calculoY / 2, 0 };

		g.setColor(color);

		Polygon polygon = new Polygon(xPoints, yPoints, 6);

		g.fillPolygon(polygon);

		g.setColor(Color.WHITE);

		g.drawLine(Math.round(getWidth() * 0.1666f), Math.round(getHeight() * 0.5f),
				getWidth() - Math.round(getWidth() * 0.1666f), Math.round(getHeight() * 0.1666f) + (calculoY / 2) + 1);

		g.drawLine(Math.round(getWidth() * 0.1666f), Math.round(getHeight() * 0.5f) + Math.round(getHeight() * 0.1666f),
				getWidth() - Math.round(getWidth() * 0.1666f),
				(Math.round(getHeight() * 0.1666f) + (calculoY / 2) + 1) + Math.round(getHeight() * 0.1666f));

		g.drawLine(Math.round(getWidth() * 0.1666f),
				Math.round(getHeight() * 0.5f) + (Math.round(getHeight() * 0.1666f) * 2),
				getWidth() - Math.round(getWidth() * 0.1666f),
				(Math.round(getHeight() * 0.1666f) + (calculoY / 2) + 1) + (Math.round(getHeight() * 0.1666f)) * 2);

	}

}
