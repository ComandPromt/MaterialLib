package com.button.rs;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonTriangle extends JButton {

	private Color colorNormal = new Color(0, 112, 192);

	private Color colorHover = new Color(67, 150, 209);

	private Color colorSecondTriangle = new Color(192, 0, 0); // Color for the second triangle

	private Font font = new Font("Tahoma", Font.BOLD, 14);

	public ButtonTriangle() {

		setFocusPainted(false);

		setFont(font);

		setPreferredSize(new Dimension(100, 100));

		setSize(new Dimension(100, 100));

		setOpaque(false);

		setContentAreaFilled(false);

		setBackground(colorNormal);

		setForeground(Color.WHITE);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	@Override
	protected void paintComponent(Graphics g) {

		if (getModel().isRollover()) {

			g.setColor(colorHover);

		}

		else {

			g.setColor(getBackground());

		}

		int width = getWidth();

		int height = getHeight();

		int[] xPoints = { width, 0, width };

		int[] yPoints = { 0, height, height };

		g.fillPolygon(xPoints, yPoints, xPoints.length);

		g.setColor(colorSecondTriangle);

		int[] x2Points = { 0, width, 0 };

		int[] y2Points = { 0, 0, height };

		g.fillPolygon(x2Points, y2Points, x2Points.length);

		super.paintComponent(g);

	}

	public Color getColorHover() {

		return colorHover;

	}

	public void setColorHover(Color colorHover) {

		this.colorHover = colorHover;

	}

	public Color getColorSecondTriangle() {

		return colorSecondTriangle;

	}

	public void setColorSecondTriangle(Color colorSecondTriangle) {

		this.colorSecondTriangle = colorSecondTriangle;

	}

}
