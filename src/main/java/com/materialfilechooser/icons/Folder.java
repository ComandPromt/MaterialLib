package com.materialfilechooser.icons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JLabel;

@SuppressWarnings("serial")

public class Folder extends JLabel {

	private Color color;

	private Color background;

	public Folder(Color color, Color background) {

		if (color == null) {

			color = Color.decode("#8b8b8b");

		}

		if (background == null) {

			background = Color.WHITE;

		}

		this.color = color;

		this.background = background;

		// setSize(getWidth() / 3, getHeight());

	}

	@Override
	public void paint(Graphics g) {

		setSize(48, 48);

		g.setColor(background);

		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(color);

		int calculo = (int) Math.round(getHeight() * 0.1666);

		int mitadX = getWidth() / 2;

		int[] xPoints = { 0, 0, getWidth(), getWidth(), mitadX, Math.round(getWidth() * 0.3333f) };

		int[] yPoints = { 0, getHeight(), getHeight(), calculo, calculo, 0 };

		Polygon polygon = new Polygon(xPoints, yPoints, xPoints.length);

		g.fillPolygon(polygon);

	}

}
